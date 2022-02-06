enum class CommandType {
    CREATE,
    READ,
    UPDATE,
    DELETE,
    WHERE,
    READALL;
}
fun reader() {
    // считываем и обрабатываем строку чтобы не было лишних пробелов
    var str = converter()
    // пока пользователь не нажал Enter
    while(str.isNotBlank()){
        // вырезаем команду
        val command = getFirstWord(str)
        // массив из ссылок на функции
        val pointers = listOf(::create, ::read, ::update, ::delete, ::where, ::readall)
        // определяем индекс enum'а
        val pointerIndex = CommandType.valueOf(command.uppercase()).ordinal
        // проверка на readall
        if(pointerIndex == 5) {
            // вызываем соответствующую функцию
            pointers[pointerIndex].call()
        } else {
            // вырезаем аргументы
            val arguments = getArguments(str)
            // вызываем соответствующую функцию
            pointers[pointerIndex].call(arguments)
        }
        str = converter()
    }
}