import re
x:int
y:int
f = "NORTH"

while True:
    # if x and y are not set, continue to next iteration of loop
    val = input("Please give command (one of PLACE, MOVE, LEFT, RIGHT, REPORT)\n").upper()
    val = re.split(' |,| ', val)
    filteredVal = list(filter(None, val))

    print("input", filteredVal)
    
    command = filteredVal[0]

    if command == "PLACE":
        x = int(filteredVal[1])
        y = int(filteredVal[2])
        f = filteredVal[3]
        print(filteredVal)
        print(x, y)
    if command == "MOVE":
        # print("before move", x, y, f )
        match f: 
            case "NORTH": 
                if y + 1 > 5: 
                    print("Robot doesn't want to fall off the table, please give another command")
                    continue
                y = y + 1
            case "EAST": 
                if x + 1 > 5: 
                    print("Robot doesn't want to fall off the table, please give another command")
                    continue
                x = x + 1
            case "SOUTH":
                if y - 1 < 0: 
                    print("Robot doesn't want to fall off the table, please give another command")
                    continue
                y = y - 1
            case "WEST":
                if x - 1 < 0: 
                    print("Robot doesn't want to fall off the table, please give another command")
                    continue
                x = x - 1
        print("after move", x, y, f)

    if command == "LEFT": 
        print("before turn", x, y, f)
        match f: 
            case "NORTH": 
                f = "WEST"
            case "EAST": 
                f = "NORTH"
            case "SOUTH":
                f = "EAST"
            case "WEST":
                f = "SOUTH"
        print("after turn", f)
    
    if command == "RIGHT": 
        print("before turn", f)
        match f: 
            case "NORTH": 
                f = "EAST"
            case "EAST": 
                f = "SOUTH"
            case "SOUTH":
                f = "WEST"
            case "WEST":
                f = "NORTH"
        print("after turn", x, y, f)
    if command == "REPORT": 
        print(x, ",", y, f)
