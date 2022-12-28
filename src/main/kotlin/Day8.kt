private typealias Trees = List<List<Tree>>

fun visibleTrees(input: String): Int {
    val treeGrid = TreeGrid(input)

    return treeGrid.visibleTrees.count()
}

fun scenicScore(input: String): Int {
    val treeGrid = TreeGrid(input)
    return treeGrid.bestScenicValue.max()
}

data class Tree(val x: Int, val y: Int, val value: Int)

data class TreeGrid(val input: String) {
    private val trees: Trees
        get() = input.splitMultiline()
            .mapIndexed { xIndex, treeRow ->
                treeRow.mapIndexed { yIndex, c ->
                    Tree(xIndex, yIndex, c.digitToInt())
                }
            }
    private val length get() = trees[0].size
    private val height get() = trees.size

    val visibleTrees
        get() = executeOnTree {
            if (onEdge()) return@executeOnTree this

            val rangeDownX = this.x - 1 downTo 0
            val rangeUpX = this.x + 1 until height
            val rangeDownY = this.y - 1 downTo 0
            val rangeUpY = this.y + 1 until length

            //Any will break earlier
            val shadowedUp = rangeDownX.any { x -> trees[x][this.y].value >= this.value }

            val shadowedDown = rangeUpX.any { x -> trees[x][this.y].value >= this.value }

            val shadowedLeft = rangeDownY.any { y -> trees[this.x][y].value >= this.value }

            val shadowedRight = rangeUpY.any { y -> trees[this.x][y].value >= this.value }

            if (arrayOf(shadowedLeft, shadowedRight, shadowedUp, shadowedDown).any { !it }) {
                return@executeOnTree this
            }

            return@executeOnTree null
        }

    val bestScenicValue
        get() = executeOnTree {
            val visibilityUp = findSmallerTreesCount(this, Direction.Up)
            val visibilityDown = findSmallerTreesCount(this, Direction.Down)
            val visibilityLeft = findSmallerTreesCount(this, Direction.Left)
            val visibilityRight = findSmallerTreesCount(this, Direction.Right)

            return@executeOnTree visibilityUp * visibilityDown * visibilityLeft * visibilityRight
        }

    private fun findSmallerTreesCount(tree: Tree, direction: Direction): Int {
        return when (direction) {
            Direction.Up -> (tree.x - 1 downTo 0).countUntil { x -> trees[x][tree.y].value >= tree.value }
            Direction.Down -> (tree.x + 1 until height).countUntil { x -> trees[x][tree.y].value >= tree.value }
            Direction.Left -> (tree.y - 1 downTo 0).countUntil { y -> trees[tree.x][y].value >= tree.value }
            Direction.Right -> (tree.y + 1 until length).countUntil { y -> trees[tree.x][y].value >= tree.value }
        }
    }

    private fun IntProgression.countUntil(predicate: (Int) -> Boolean): Int =
        fold(0) { acc, i ->
            val current = acc + 1
            if (predicate(i)) {
                return@countUntil current
            }
            current
        }


    private fun Tree.onEdge(): Boolean {
        if (this.x == 0 || this.y == 0 || this.x == height - 1 || this.y == length - 1) {
            return true
        }
        return false
    }

    private fun <T> executeOnTree(action: Tree.() -> T) =
        trees.flatMap { row ->
            row.mapNotNull { tree ->
                action(tree)
            }
        }
}