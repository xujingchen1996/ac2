/**
 * AC - A source-code copy detector
 *
 *     For more information please visit:  http://github.com/manuel-freire/ac
 *
 * ****************************************************************************
 *
 * This file is part of AC, version 2.0
 *
 * AC is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * AC is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AC.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * CompositeFilter.java
 *
 * Created on September 11, 2006, 3:43 PM
 *
 */

package es.ucm.fdi.ac.extract;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

/**
 * A filter that contains nested filters.
 * 
 * @author mfreire
 */
public class CompositeFilter extends FileTreeFilter {

	public enum Operator {
		Not, And, Or
	};

	private final ArrayList<FileTreeFilter> filters = new ArrayList<FileTreeFilter>();
	private Operator op = Operator.And;

	public void saveInner(Element e) throws IOException {
		e.setAttribute("operation", op.toString().toLowerCase());

		// Add child filters
		for (FileTreeFilter filter : filters) {
			e.addContent(filter.saveToXML());
		}
	}

	public void loadFromXML(Element filterElement) throws IOException {
		setOp(filterElement.getAttributeValue("operation"));
		filters.clear();

		List<Element> children = filterElement.getChildren();
		for (Element e : children) {
			try {
				Class filterClass = Class.forName(e.getAttributeValue("class"));
				FileTreeFilter f = (FileTreeFilter) filterClass.newInstance();
				f.loadFromXML(e);
				filters.add(f);
			} catch (ClassNotFoundException ex) {
				throw new IOException(ex);
			} catch (InstantiationException ex) {
				throw new IOException("Could not instantiate "
						+ e.getAttributeValue("class"), ex);
			} catch (IllegalAccessException ex) {
				throw new IOException(ex);
			}
		}
	}

	public void setOp(Operator op) {
		if (filters.size() != 1 && op.equals(Operator.Not)) {
			throw new IllegalArgumentException(
					"'Not' operator requires exactly 1 argument");
		}
		this.op = op;
	}

	public Operator getOp() {
		return op;
	}

	public void clear() {
		filters.clear();
		op = Operator.And;
	}

	public void removeFilter(FileTreeFilter f) {
		if (filters.size() == 1 && op.equals(Operator.Not)) {
			throw new IllegalArgumentException(
					"Cannot remove that many filters from a Not operation");
		}
		filters.remove(f);
	}

	public void addFilter(FileTreeFilter f) {
		if (filters.size() > 0 && op.equals(Operator.Not)) {
			throw new IllegalArgumentException(
					"Unexpected second argument to 'Not' operator");
		}
		filters.add(f);
	}

	public ArrayList<FileTreeFilter> getFilters() {
		return filters;
	}

	public boolean accept(FileTreeNode fn) {
		switch (op) {
		case Not:
			return !filters.get(0).accept(fn);
		case Or:
			for (FileTreeFilter ff : filters) {
				if (ff.accept(fn))
					return true;
			}
			return false;
		case And:
			for (FileTreeFilter ff : filters) {
				if (!ff.accept(fn))
					return false;
			}
			return true;
		default:
			throw new RuntimeException(
					"Bad operation in boolean-composite-filter");
		}
	}

	public boolean accept(File f) {
		switch (op) {
		case Not:
			return !filters.get(0).accept(f);
		case Or:
			for (FileTreeFilter ff : filters) {
				if (ff.accept(f))
					return true;
			}
			return false;
		case And:
			for (FileTreeFilter ff : filters) {
				if (!ff.accept(f))
					return false;
			}
			return true;
		default:
			throw new RuntimeException(
					"Bad operation in boolean-composite-filter");
		}
	}

	/**
	 * Sets node boolean operation.
	 * This method can be used instead of Operator.valueOf since
	 * it provides more flexible syntax (it is case insensitive).
	 * @param op string of the operation
	 */
	public void setOp(String op) {
		String operation = op.toLowerCase();
		if (operation.equals("not")) {
			setOp(Operator.Not);
		} else if (operation.equals("or")) {
			setOp(Operator.Or);
		} else if (operation.equals("and")) {
			setOp(Operator.And);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (FileTreeFilter f : filters) {
			sb.append(f).append(" ");
		}
		sb.append("]");
		return "" + op + " " + sb.toString();
	}
}
