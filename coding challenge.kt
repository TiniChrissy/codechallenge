fun main() {
    toyRobotControl()
}
data class Position(                                          // Create position dataclass
    var x: Int,
    var y: Int,
    var face: String                       // Provide a default value for the argument
)
//Magic strings
const val place = "place"
val move = "move"
val left = "left"
val right = "right"
val report = "report"

val north = "north"
val east = "east"
val south = "south"
val west = "west"

fun toyRobotControl() {
    var currentRobotPosition = Position(0, 0, north)

    var firstIteration = true
    while (true) {
        val userInput = input()
        val command = userInput[0]

        var robotPosition = Position(1, 1, north)
        if (command == place) {
            robotPosition = parseInput(userInput)
            firstIteration = false
        }

        if (firstIteration && command != place) {
            println("Please first enter place command. e.g: place 4,5 east")
            continue
        }

        when(command) {
            place -> currentRobotPosition = place(robotPosition)
            move -> currentRobotPosition = move(currentRobotPosition)
            left -> currentRobotPosition = turn(left, currentRobotPosition)
            right -> currentRobotPosition = turn(right, currentRobotPosition)
            report -> report(currentRobotPosition)
        }
    }
}
fun input() : List<String> {
    print("Please give command (one of PLACE, MOVE, LEFT, RIGHT, REPORT)\n")
    val input = readLine()?.lowercase()
    val splitInput = input?.split(",", " ")?.toMutableList()
    splitInput?.remove("")
    println("split input $splitInput") //testing purposes, to be deleted
    val returnValue: List<String>

    try {
        returnValue = splitInput!!.toList()
    } catch (e: IndexOutOfBoundsException) {
        print("IndexOutOfBoundsException")
        return emptyList()
    }
    return returnValue
}

fun parseInput(input: List<String>): Position {
    try {
        if (input != null && input.count() > 1) {
            //the command should be place "place 1, 3 north"
            val x = input[1].toInt()
            val y = input[2].toInt()
            val face = input[3]
            return Position(x, y, face)
        }
    } catch (e: Exception) {
        print("help") //delete
    }
    return  Position(2, 2, north)
}
fun place(position: Position): Position {
    return position
}

fun move(position: Position): Position {
    val (x, y, face) = position
    when (face) {
        north ->  {
            if (y + 1 > 5) {
                println("Robot doesn't want to fall off the table, please give another command")
                input()
                return position
            }
            return Position(x, y+ 1, north)
        }
//            if y + 1 > 5:
//        print("Robot doesn't want to fall off the table, please give another command")
//        continue
//                y = y + 1
        east -> return Position(x, y, north)
        south -> return Position(x, y, east)
        west -> return Position(x, y, south)
    }
    return position
}

fun turn(direction: String, position: Position): Position{
    if (direction == left) {
        val (x, y, face) = position
        when (face) {
            north -> return Position(x, y, west)
            east -> return Position(x, y, north)
            south -> return Position(x, y, east)
            west -> return Position(x, y, south)
        }
    }

    if (direction == right) {
        val (x, y, face) = position
        when (face) {
            north -> return Position(x, y, east)
            east -> return Position(x, y, south)
            south -> return Position(x, y, west)
            west -> return Position(x, y, north)
        }
    }

    return Position(3, 3, north) //Should never occur
}

fun report(position: Position) {
    val (x, y, face) = position
    println("$x, $y, $face")
}
