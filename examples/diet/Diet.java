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

import jCMPL.*;

import java.util.ArrayList;


public class Diet {

    public static void main(String[] args) throws CmplException {
       try {
            Cmpl model = new Cmpl("diet.cmpl");

            CmplSet nutr = new CmplSet("NUTR");
            ArrayList<String> nutrLst = new ArrayList<String>();
            nutrLst.add("A");
            nutrLst.add("B1");
            nutrLst.add("B2");
            nutrLst.add("C");
            nutr.setValues(nutrLst);

            CmplSet food = new CmplSet("FOOD");
            String[] foodLst = {"BEEF", "CHK", "FISH", "HAM", "MCH", "MTL", "SPG", "TUR"};
            food.setValues(foodLst);

            CmplParameter costs = new CmplParameter("costs", food);
            Double[] costVec = {3.19, 2.59, 2.29, 2.89, 1.89, 1.99, 1.99, 2.49};
            costs.setValues(costVec);

            CmplParameter vitmin = new CmplParameter("vitMin", nutr);
			int [] vitminVec = { 700,700,700,700};
			vitmin.setValues(vitminVec);

            CmplParameter vitamin = new CmplParameter("vitamin", nutr, food);
            int[][] vitMat = {{60, 8, 8, 40, 15, 70, 25, 60}, {20, 0, 10, 40, 35, 30, 50, 20}, {10, 20, 15, 35, 15, 15, 25, 15}, {15, 20, 10, 10, 15, 15, 15, 10}};
            vitamin.setValues(vitMat);

            model.setSets(nutr, food);
            model.setParameters(costs, vitmin, vitamin);
                 
            //start CmplServer first with cmplServer -start
            //model.connect("http://127.0.0.1:8008");
            
            model.setOutput(true);
            model.solve();

            model.solutionReport();


        } catch (CmplException e) {
            System.out.println(e);

        }
    }
}
