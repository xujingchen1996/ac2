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
 * HistogramRenderer.java
 *
 * Created on September 7, 2006, 4:12 PM
 *
 */

package es.ucm.fdi.ac.hist;

import java.awt.Graphics;

/**
 * A HistogramRenderer is in charge of actually displaying a histogram on-screen.
 * It is a lightweight renderer: it stores no internal data.
 *
 * @author mfreire
 */
public interface HistogramRenderer {

	/**
	 * Retrieves the number of bars that this renderer would use for the given
	 * histogram
	 */
	public int getBarCount(Histogram h);

	/**
	 * Paints the given histogram
	 */
	public void paint(Graphics g, Histogram h);
}
