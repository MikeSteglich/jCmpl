---------------------------------------------------------------------------------------------------------
Problem              diet-with-externals.cmpl
Nr. of variables     8
Nr. of constraints   4
Objective name       cost
Solver name          GLPK
Display variables    (all)
Display vonstraints  (all)
---------------------------------------------------------------------------------------------------------

Objective status     optimal
Objective value      101.14               (min!)

Variables           
Name                 Type            Activity          LowerBound          UpperBound            Marginal
---------------------------------------------------------------------------------------------------------
x[BEEF]                 I                   2                2.00               10.00                   -
x[CHK]                  I                   8                2.00               10.00                   -
x[FISH]                 I                   2                2.00               10.00                   -
x[HAM]                  I                   2                2.00               10.00                   -
x[MCH]                  I                  10                2.00               10.00                   -
x[MTL]                  I                  10                2.00               10.00                   -
x[SPG]                  I                  10                2.00               10.00                   -
x[TUR]                  I                   2                2.00               10.00                   -
---------------------------------------------------------------------------------------------------------

Constraints         
Name                 Type            Activity          LowerBound          UpperBound            Marginal
---------------------------------------------------------------------------------------------------------
line[A]                 G             1500.00              700.00            Infinity                   -
line[B1]                G             1330.00              700.00            Infinity                   -
line[B2]                G              860.00              700.00            Infinity                   -
line[C]                 G              700.00              700.00            Infinity                   -
---------------------------------------------------------------------------------------------------------
