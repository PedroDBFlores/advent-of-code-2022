fun visibleTrees(input: String): Int {
    val treeGrid = TreeGrid(input)

    return treeGrid.visibleTrees.count()
}

data class Tree(val x: Int, val y: Int, val value: Int)

data class TreeGrid(val input: String) {
    private val trees: List<List<Tree>>
        get() = input.splitMultiline()
            .mapIndexed { xIndex, treeRow ->
                treeRow.mapIndexed { yIndex, c ->
                    Tree(xIndex, yIndex, c.digitToInt())
                }
            }
    private val length get() = trees[0].size
    private val height get() = trees.size

    val visibleTrees
        get() = trees.flatMap { row ->
            row.mapNotNull { tree ->
                if (tree.x == 0 || tree.y == 0 || tree.x == height - 1 || tree.y == length - 1) {
                    return@mapNotNull tree
                }

                val rangeDownX = tree.x - 1 downTo 0
                val rangeUpX = tree.x + 1 until height
                val rangeDownY = tree.y - 1 downTo 0
                val rangeUpY = tree.y + 1 until length

                //Any will break earlier
                val shadowedLeft = rangeDownX.any { x -> trees[x][tree.y].value >= tree.value }

                val shadowedRight = rangeUpX.any { x -> trees[x][tree.y].value >= tree.value }

                val shadowedUp = rangeDownY.any { y -> trees[tree.x][y].value >= tree.value }

                val shadowedDown = rangeUpY.any { y -> trees[tree.x][y].value >= tree.value }

//                println("$tree, SL: $shadowedLeft, SR: $shadowedRight, SU: $shadowedUp, SD: $shadowedDown")

                if (arrayOf(shadowedLeft, shadowedRight, shadowedUp, shadowedDown).any { !it }) {
                    return@mapNotNull tree
                }

                return@mapNotNull null
            }
        }
}