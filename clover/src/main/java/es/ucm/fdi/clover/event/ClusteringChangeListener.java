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
 * ClusteringChangeListener.java
 *
 * Created on May 16, 2006, 12:26 AM
 * Original Author: Manuel Freire (manuel.freire@uam.es)
 */

package es.ucm.fdi.clover.event;

/**
 * Implementors will be messaged when the clustering changes
 * 
 * @author mfreire
 */
public interface ClusteringChangeListener {
	void clusteringChangePerformed(ClusteringChangeEvent evt);
}
