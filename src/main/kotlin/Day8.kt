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

                val visibleLeft = rangeDownX.map { x ->
                    return@map trees[x][tree.y].value < tree.value
                }.count { it } == rangeDownX.count()

                val visibleRight = rangeUpX.map { x ->
                    return@map trees[x][tree.y].value < tree.value
                }.count { it } == rangeUpX.count()

                val visibleUp = rangeDownY.map { y ->
                    return@map trees[tree.x][y].value < tree.value
                }.count { it } == rangeDownY.count()

                val visibleDown = rangeUpY.map { y ->
                    return@map trees[tree.x][y].value < tree.value
                }.count { it } == rangeUpY.count()

                if (visibleLeft || visibleRight || visibleUp || visibleDown) {
                    return@mapNotNull tree
                }

                return@mapNotNull null
            }
        }
}