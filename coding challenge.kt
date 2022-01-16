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
const val move = "move"
const val left = "left"
const val right = "right"
const val report = "report"

const val north = "north"
const val east = "east"
const val south = "south"
const val west = "west"

fun toyRobotControl() {
    var currentRobotPosition = Position(999, 999, north)

    var firstIteration = true
    while (true) {
        val userInput = input()
        val command = if (userInput.isEmpty()) continue else userInput[0]

        var robotPosition = Position(1, 1, north)
        if (command == place) {
            robotPosition?.also { } ?: continue

            var temp = parsePosition(userInput)
            temp?.also { var notNull = temp } ?: continue
            parsePosition(userInput)
            firstIteration = false
        }

        if (currentRobotPosition.x == 999 && command != place) {
            println("Please first enter a valid place command. e.g: place 4,5 east")
            continue
        }

        when(command) {
            place -> currentRobotPosition = place(robotPosition)
            move -> currentRobotPosition = move(currentRobotPosition)
            left -> currentRobotPosition = turn(left, currentRobotPosition)
            right -> currentRobotPosition = turn(right, currentRobotPosition)
            report -> report(currentRobotPosition)
            else -> {
                println("Perhaps you misspelled your command?")
            }
        }
    }
}
fun input() : List<String> {
    print("Please give command (one of PLACE, MOVE, LEFT, RIGHT, REPORT)\n")
    val input = readLine()?.lowercase()
    val splitInput = input?.split(",", " ")?.toMutableList()
    splitInput?.removeAll(listOf(""))
    println("split input $splitInput, count ${splitInput?.count()}") //testing purposes, to be deleted
    val returnValue: List<String>

    try {
        returnValue = splitInput!!.toList()
    } catch (e: IndexOutOfBoundsException) {
        print("IndexOutOfBoundsException")
        return emptyList()
    }
    return returnValue
}

fun parsePosition(input: List<String>): Position? {
    try {
        if (input.count() > 1) {
            //the command should be place "place 1, 3 north"
            val x = input[1].toInt()
            val y = input[2].toInt()
            val face = input[3]

            if (x in 0.. 5 && y in 0.. 5) {
                return Position(x, y, face)
            } else {
                println("Please place robot on a grid of 5 x 5")
                return null
            }

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
                return position
            }
            return Position(x, y+ 1, north)
        }
        east ->  {
            if (x + 1 > 5) {
                println("Robot doesn't want to fall off the table, please give another command")
                return position
            }
            return Position(x+1, y, east)
        }

        south ->  {
            if (y - 1 < 0) {
                println("Robot doesn't want to fall off the table, please give another command")
                return position
            }
            return Position(x, y - 1, south)
        }

        west ->  {
            if (x - 1 < 0) {
                println("Robot doesn't want to fall off the table, please give another command")
                return position
            }
            return Position(x -1, y, west)
        }

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