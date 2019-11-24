@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    when {
        (age % 10 == 1) && (age != 11) && (age != 111) || (age == 1) -> "$age год"
        (age > 20 && age !in 105..120) && (age % 10 in 2..4) || (age in 2..4) -> "$age года"
        (age in 100..120) || (age in 5..20) -> "$age лет"
        else -> "$age лет"
    }

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {

    val s = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    return when {
        ((v1 * t1 + t2 * v2) < s) -> t1 + t2 + (s - (t1 * v1 + t2 * v2)) / v3
        ((v1 * t1) < s) -> t1 + ((s - (t1 * v1)) / v2)
        else -> s / v1
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int,
    kingY: Int,
    rookX1: Int,
    rookY1: Int,
    rookX2: Int,
    rookY2: Int
): Int {
    val rookTrue1 = (kingY == rookY1) || (kingX == rookX1)
    val rookTrue2 = (kingX == rookX2) || (kingY == rookY2)
    val rookFalse1 = !rookTrue1
    val rookFalse2 = !rookTrue2
    return when {
        (rookFalse1) && (rookFalse2) -> 0
        (rookFalse1) -> 2
        (rookTrue2) -> 3
        else -> 1
    }
}


/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val bishopTrue = (abs(bishopX - kingX) == abs(bishopY - kingY))
    val bishopFalse = !bishopTrue
    val rookTrue = ((kingX != rookX) && (kingY == rookY) || (kingX == rookX) && (kingY != rookY))
    val rookFalse = !rookTrue
    return when {
        (rookFalse && bishopFalse) -> 0
        (rookFalse) -> 2
        (bishopTrue) -> 3
        else -> 1
    }
}


/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val aSumB = b.pow(2) + a.pow(2)
    val bSumC = b.pow(2) + c.pow(2)
    val aSumC = a.pow(2) + c.pow(2)
    when {
        (a >= b + c || b >= a + c || c >= b + a) -> return -1
        (a.pow(2) == bSumC) -> return 1
        (a.pow(2) > bSumC || b.pow(2) > aSumC || c.pow(2) > aSumB) -> return 2
        (a.pow(2) < bSumC || b.pow(2) < aSumC || c.pow(2) < aSumB) -> return 0
    }
    return 0
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(
    a: Int,
    b: Int,
    c: Int,
    d: Int
): Int {
    val l = minOf(b, d) - max(a, c)
    return if (l < 0) -1
    else l
}








