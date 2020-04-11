import kotlin.random.Random

class Maze(private val width: Int, private val height: Int) {

    private enum class Cell {
        WALL, SPACE
    }

    private val data = Array(width) { Array(height) { Cell.WALL} }

    init {
        generate()
    }

    private fun carve(x: Int, y: Int) {

        val upx = intArrayOf(1, -1, 0, 0)
        val upy = intArrayOf(0, 0, 1, -1)

        var dir = Random.nextInt(until = 4)
        var count = 0
        while(count < 4) {
            val x1 = x + upx[dir]
            val x2 = x1 + upx[dir]
            val y1 = y + upy[dir]
            val y2 = y1 + upy[dir]

            if(data[x1][y1] == Cell.WALL && data[x2][y2] == Cell.WALL) {
                data[x1][y1] = Cell.SPACE
                data[x2][y2] = Cell.SPACE
                carve(x2, y2)
            } else {
                dir = (dir + 1) % 4
                count += 1
            }
        }

    }

    fun generate() {

        for(x in 0 until width) {
            for(y in 0 until height) {
                data[x][y] = Cell.WALL
            }
        }

        for(x in 0 until width) {
            data[x][0] = Cell.SPACE
            data[x][height - 1] = Cell.SPACE
        }
        for(y in 0 until height) {
            data[0][y] = Cell.SPACE
            data[width - 1][y] = Cell.SPACE
        }

        data[2][2] = Cell.SPACE
        carve(2, 2)

        data[2][1] = Cell.SPACE
        data[width - 3][height - 2] = Cell.SPACE
    }

    fun print() {
        for(y in 0 until height) {
            for(x in 0 until width) {
                if(data[x][y] == Cell.WALL) {
                    print("[]")
                } else {
                    print("  ")
                }
            }
            println()
        }
    }

}

fun main() {
    val m = Maze(25, 21) //must be odd
    m.generate()
    m.print()
}