import re
x:int
y:int
f = "N"


while True:
    # if x and y are not set, continue to next iteration of loop
    # numbers = line.split()
    val = input("Please give command (one of PLACE, MOVE, LEFT, RIGHT, REPORT)\n").lower()
    aa = re.split(', |\n', val)
    print(aa)
    # print(x, y)

    if val == "place":
        print("d")
    if val == "move" and x :
        print("before move", x, y, f )
        match f: 
            case "N": 
                if y + 1 > 5: 
                    print("Robot doesn't want to fall of the table, please give another command")
                y = y + 1
            case "E": 
                x = x + 1
            case "S":
                y = y - 1
            case "W":
                x = x - 1
        print("after move", x, y, f)

    if val == "left": 
        print("before turn", x, y, f)
        match f: 
            case "N": 
                f = "W"
            case "E": 
                f = "N"
            case "S":
                f = "E"
            case "W":
                f = "S"
        print("after turn", getCurrentFace())
    
    if val == "right": 
        print("before turn", getCurrentFace())
        match f: 
            case "N": 
                f = "E"
            case "E": 
                f = "S"
            case "S":
                f = "W"
            case "W":
                f = "N"
        print("after turn", x, y, f)
    if val == "report": 
        print(x, y, f)
