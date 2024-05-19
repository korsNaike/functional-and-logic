package LR5

import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Пожалуйста, укажите путь к файлу в качестве аргумента командной строки.")
        return
    }

    val filePath = args[0]
    val inputFile = File(filePath)

    if (!inputFile.exists() || !inputFile.isFile) {
        println("Файла по заданному пути не существует.")
        return
    }

    val outputFile = File("files/numberFunctions/output.txt")

    inputFile.forEachLine { line ->
        val parts = line.split(" ")
        if (parts.size != 2) {
            outputFile.appendText("Неверный формат строки: $line\n")
            return@forEachLine
        }

        val number = parts[0].toIntOrNull() ?: return@forEachLine
        val functionName = parts[1]

        val rec = RecursionNumberFunctions()
        val function = rec.getFunction(functionName)
        if (function == null) {
            outputFile.appendText("Неверное имя функции: $functionName\n")
            return@forEachLine
        }

        val result = function(number)
        outputFile.appendText("$number $functionName $result\n")
    }

    println("Был успешно создан файл: ${outputFile.absolutePath}")
}
