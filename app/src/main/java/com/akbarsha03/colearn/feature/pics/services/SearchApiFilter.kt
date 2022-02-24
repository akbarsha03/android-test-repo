package com.akbarsha03.colearn.feature.pics.services

object SearchApiFilter {

    enum class OrderBy(val order: String) {
        LATEST("latest"),
        RELEVANT("relevant"),
    }

    enum class ContentFilter(val content: String) {
        LOW("low"),
        HIGH("high"),
    }

    enum class ColorFilter(val color: String) {
        BLACK_AND_WHITE("black_and_white"),
        WHITE("white"),
        BLACK("black"),
        YELLOW("yellow"),
        ORANGE("orange"),
        RED("red"),
        PURPLE("purple"),
        MAGENTA("magenta"),
        GREEN("green"),
        TEAL("teal"),
        BLUE("blue"),
    }

    enum class OrientationFilter(val orientation: String) {
        LANDSCAPE("landscape"),
        PORTRAIT("portrait"),
        SQUARISH("squarish"),
    }
}