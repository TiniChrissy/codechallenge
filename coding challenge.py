import re
x:int
y:int
f = "first"

while True:
    val = input("Please give command (one of PLACE, MOVE, LEFT, RIGHT, REPORT)\n").upper()
    val = re.split(' |,| ', val)
    filteredVal = list(filter(None, val))    
    command = filteredVal[0]

    match command:
        case "PLACE":
            if len(filteredVal) != 4:
                print("Invalid number of input")
                continue
            try:
                x = int(filteredVal[1])
            except ValueError:
                print("x: Please enter an integer between 0 and 5")
                continue

            try:
                y = int(filteredVal[2])
            except ValueError:
                print("y: Please enter an integer between 0 and 5")
                continue           
            
            f = filteredVal[3]
            if f not in ["NORTH", "EAST", "SOUTH", "WEST"]:
                print("Invalid direction, please enter one of North, South, East, or West")
                continue

        case "MOVE":
            if f == "first":
                continue
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

        case "LEFT":
            if f == "first":
                continue
            match f: 
                case "NORTH": 
                    f = "WEST"
                case "EAST": 
                    f = "NORTH"
                case "SOUTH":
                    f = "EAST"
                case "WEST":
                    f = "SOUTH"

        case "RIGHT":
            if f == "first":
                continue
            match f: 
                case "NORTH": 
                    f = "EAST"
                case "EAST": 
                    f = "SOUTH"
                case "SOUTH":
                    f = "WEST"
                case "WEST":
                    f = "NORTH"

        case "REPORT":
            if f == "first":
                continue
            print(x, ",", y, f)
            
        case _:
            print("Not a valid command")
