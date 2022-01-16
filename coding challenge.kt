fun main() {
    toyRobotControl()
}

// Position class holds all relevant information about robot positioning
data class Position(
    var x: Int,
    var y: Int,
    var face: String                // The direction the robot is facing
)

//Valid commands
const val place = "place"
const val move = "move"
const val left = "left"
const val right = "right"
const val report = "report"

//Valid directions/face
const val north = "north"
const val east = "east"
const val south = "south"
const val west = "west"

val directions = listOf(north, east, south, west)
const val outOfBoundsCoordinate = 999

fun toyRobotControl() {
    // Default robot position is not on the board, used to check if placed yet
    var robotPosition = Position(outOfBoundsCoordinate, outOfBoundsCoordinate, north)

    while (true) {
        val userInput = input()
        val command = if (userInput.isEmpty()) continue else userInput[0]

        // Check that first command is a valid place command
        // Only way that outOfBoundsCoordinate is possible is if the place command hasn't run yet
        if (robotPosition.x == outOfBoundsCoordinate && command != place) {
            println("Please first enter a valid place command. e.g: place 4,5 east")
            continue
        }

        // currentRobotPosition is constantly updated/used by various functions/commands
        when(command) {
            place -> {
                val newRobotPosition = parsePosition(userInput) ?: continue
                robotPosition = place(newRobotPosition)
            }
            move -> robotPosition = move(robotPosition)
            left -> robotPosition = turn(left, robotPosition)
            right -> robotPosition = turn(right, robotPosition)
            report -> report(robotPosition)
            else -> {
                println("Perhaps you misspelled your command?")
            }
        }
    }
}

// Read input from user, remove delimiters and whitespace, return in array format
fun input() : List<String> {
    print("Please give command (one of PLACE, MOVE, LEFT, RIGHT, REPORT)\n")
    val input = readLine()?.lowercase()
    val splitInput = input?.split(",", " ")?.toMutableList()
    splitInput?.removeAll(listOf(""))
    val returnValue: List<String>

    try {
        returnValue = splitInput!!.toList()
    } catch (e: IndexOutOfBoundsException) {
        print("IndexOutOfBoundsException")
        return emptyList()
    }
    return returnValue
}

// Input: User input in the expected format converted to a list of strings e.g. [place, 4, 4, east]
// Output: Position class
fun parsePosition(input: List<String>): Position? {
    if (input.count() != 4) {
        println("Incorrect number of inputs provided for command")
        return null
    }
    val x = input[1].toInt()
    val y = input[2].toInt()
    val face = input[3]

    // If position is within 5x5 boundary return position
    if (x in 0.. 5 && y in 0.. 5 && face in directions) {
        return Position(x, y, face)
    } else { //Otherwise, return null position
    println("Please place robot on a grid of 5 x 5 with a valid direction")
    return null
    }

}

// Slightly useless but it's to hide implementation details and for consistency
fun place(position: Position): Position {
    return position
}

// input: starting position
// output: position after attempted move
fun move(startingPosition: Position): Position {
    val (x, y, face) = startingPosition
    when (face) {
        north ->  {
            if (y + 1 > 5) {
                println("Robot doesn't want to fall off the table, please give another command")
                return startingPosition
            }
            return Position(x, y+ 1, north)
        }
        east ->  {
            if (x + 1 > 5) {
                println("Robot doesn't want to fall off the table, please give another command")
                return startingPosition
            }
            return Position(x+1, y, east)
        }

        south ->  {
            if (y - 1 < 0) {
                println("Robot doesn't want to fall off the table, please give another command")
                return startingPosition
            }
            return Position(x, y - 1, south)
        }

        west ->  {
            if (x - 1 < 0) {
                println("Robot doesn't want to fall off the table, please give another command")
                return startingPosition
            }
            return Position(x -1, y, west)
        }

    }
    return startingPosition
}

// Returns a new position with a rotated face based on direction.
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
    return Position(3, 3, north) // Necessary return, but this should never occur.
}

// Print position based on input position
fun report(position: Position) {
    val (x, y, face) = position
    println("$x, $y, $face")
}