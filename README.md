# Work-hours-availability

Code in Java
Reads the following file: 
# example input for the find availability problem

# company work hours
0,8,8,8,8,8,0

# employees
1,Mark Virtue
2,Anne Prins

# mark's availability
1,"01/01/2015","12/31/2015",[0,8,8,4,10,10,0]
1,"01/01/2016",null,[0,8,8,8,8,8,0]

# anne's availability
2,"03/16/2016","06/16/2016",[0,8,8,8,8,0,0]

# find availability for user and dates
1,"12/16/2015","01/15/2016"

Assure that the output is:
"12/06/2015",0
"12/07/2015",4
"12/08/2015",4
"12/09/2015",8

While using the funtion find_availabile_work_hours(1,"12/06/2015","12/09/2015"). It is okay to ignore # lines and empty lines.
