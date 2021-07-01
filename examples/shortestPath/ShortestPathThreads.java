/* ****************************************************************************
 * This code is part of jCMPL
 *
 * Copyright (C) 2013 Mike Steglich / B. Knie Technical University of Applied
 * Sciences Wildau, Germany
 *
 * jCMPL is a project of the Technical University of Applied Sciences Wildau
 * and the Institute for Operations Research and Business Management at the
 * Martin Luther University Halle-Wittenberg.
 *
 * Please visit the project homepage <http://www.coliop.org>
 *
 * jCMPL is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * jCMPL is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public # License for more
 * detailm.solution().
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 * ****************************************************************************/

import java.util.ArrayList;
import jCMPL.*;

public class ShortestPathThreads {
    public static void main(String[] args) throws CmplException {
        
        try {
            CmplSet routes = new CmplSet("A",2);
            int[][] arcs = {{1,2},{1,4},{2,1},{2,3},{2,4},{2,5},
				 {3,2},{3,5},{4,1},{4,2},{4,5},{4,6},
				 {5,2},{5,3},{5,4},{5,6},{5,7},
				 {6,4},{6,5},{6,7},{7,5},{7,6}};
            routes.setValues(arcs);
        
            CmplSet nodes = new CmplSet("V");
            nodes.setValues(1,7);
            
            Integer[] cArr = {7,5,7,8,9,7,8,5,5,9,15,6,7,5,15,8,9,6,8,11,9,11};
           
            CmplParameter sNode = new CmplParameter("s");
            sNode.setValues(1);
            
            CmplParameter tNode = new CmplParameter("t");
            tNode.setValues(7);
            
            ArrayList<Cmpl> models = new ArrayList<Cmpl>();
			ArrayList<CmplParameter> randC = new ArrayList<CmplParameter>();
           
			for (int i = 0; i  < 5; i++) {
				models.add(new Cmpl("shortest-path.cmpl") );
				models.get(i).setSets(routes, nodes);        
				randC.add(new CmplParameter("c", routes) );
                
				ArrayList<Double> tmpC = new ArrayList<Double>();
				for (Integer cArr1 : cArr) {
					tmpC.add(Double.valueOf(cArr1) + 
							Double.valueOf( -40 +  (Math.random() *  40) )/10);
				}
				randC.get(i).setValues(tmpC);
				models.get(i).setParameters(randC.get(i), sNode, tNode);
				models.get(i).setOption("-display nonZeros");
			}

            for (Cmpl c : models) {
				c.start();
			}
            
			for (Cmpl c : models) {
				c.join();
			}

			int i = 1;
			for (Cmpl c : models) {
				System.out.println("model : " + String.valueOf(i) + 
						" needed time : " + c.solution().value());

				for (CmplSolElement v : c.solution().variables()) {
					System.out.println(v.name() + " " + v.activity());
				}
				i++;
			}
        } catch (CmplException | InterruptedException e) {
            System.out.println(e);
        } 
    }
}
