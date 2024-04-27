% write_list_str(+String) - напечатать строку
write_list_str([]):-!.
write_list_str([H|List]):-write(H), write_list_str(List).

% read_str_one(+CurrentChar, +CurrentStr, -ResultStr, -Flag) - прочитать 1 строку, записать в ResultStr, и вернуть флаг, 1 - переход на новую строку , 0 - конец файла
read_str_one(-1,CurrentStr,CurrentStr,0):-!.
read_str_one(10,CurrentStr,CurrentStr,1):-!.
read_str_one(26,CurrentStr,CurrentStr,0):-!.
read_str_one(CurrentSymbol, CurrentStr, ResultStr, Flag):- 
    char_code(ResSymbol, CurrentSymbol),
    append(CurrentStr,[ResSymbol],NewCurrentStr),
    get0(NewSymbol),
    read_str_one(NewSymbol,NewCurrentStr,ResultStr,Flag).

% read_all_str(-ResultList) - считать все строки до конца строки или файла.
read_all_str(ResultList):- read_all_str(ResultList, [], 1), !.

% read_all_str(-ResultList, +CurrentList, +Flag)
read_all_str(CurrentList, CurrentList, 0):-!.
read_all_str(StrList, CurrentList, _):-
    get0(NewSymbol), 
    read_str_one(NewSymbol,[],ResultStr,Flag),
    append(CurrentList,[ResultStr],NewStrList),
    read_all_str(StrList,NewStrList,Flag).

% read_file_strings_in_list(+FilePath, -ListOfStrings) - считать все строки из файла в виде списка строк
read_file_strings_in_list(FilePath, ListOfStrings):-see(FilePath), read_all_str(ListOfStrings), seen.

% write_list_of_lists(+List) - вывести список списков (список строк)
write_list_of_lists([]):-!.
write_list_of_lists([H|TailListOfLists]):-write_list_str(H), nl, write_list_of_lists(TailListOfLists),!.

% write_to_file(+FilePath, +ListOfStrings) - записать в файл список строк
write_to_file(FilePath, ListOfStrings):- tell(FilePath), write_list_of_lists(ListOfStrings), told, !.


%max(+X, +Y, -Result) - записать в Result максимальное значение
max(X, Y, X):- X > Y, !.
max(_, Y, Y):-!.

% length_of(+ListOrString, -Length) - записать в Length длину списка/строки
length_of(List, Length):- length_of(List, 0, Length), !.
length_of([],CurrentLen,CurrentLen):- !.
length_of([_|T], CurrentLen, Length):- NewLen is CurrentLen + 1, length_of(T, NewLen, Length), !.

% max_length_in_list(+ListOfLists, -MaxLength) - записать в MaxLength максимальную длину списка/строки внутри списка
max_length_in_list(ListOfLists, MaxLength):- max_length_in_list(ListOfLists, 0, MaxLength), !.

max_length_in_list([], MaxLength, MaxLength):- !.
max_length_in_list([String|TailListOfStrings], CurrentMax, MaxLength):-
    length_of(String, CurrentLen),
    max(CurrentLen, CurrentMax, NewMax),  
    max_length_in_list(TailListOfStrings, NewMax, MaxLength).

% find_max_length_string_in_file(+FilePath, -MaxLength) - получить максимальную длину строки в файле
find_max_length_string_in_file(FilePath, MaxLength):- read_file_strings_in_list(FilePath, StringList), max_length_in_list(StringList, MaxLength), !.

% string_has_space(+String) - есть ли в строке/списке символов символ пробела
string_has_space([]):- !, fail.
string_has_space([HeadString|_]):- char_code(HeadString, SymbolCode), SymbolCode is 32, !.
string_has_space([_|TailString]):- string_has_space(TailString).

% count_strings_with_spaces(+ListStrings, -Result) - посчитать количество строк без пробелов в списке строк
count_strings_without_spaces(ListStrings, Result):- count_strings_without_spaces(ListStrings, 0, Result), !.
count_strings_without_spaces([], Result, Result):- !.
count_strings_without_spaces([String|TailStrings], CurCount, Result):- string_has_space(String), count_strings_without_spaces(TailStrings, CurCount, Result), !.
count_strings_without_spaces([_|TailStrings], CurCount, Result):- NewCurCount is CurCount + 1, count_strings_without_spaces(TailStrings, NewCurCount, Result), !.

% count_strings_without_spaces_in_file(+FilePath, -Count) - посчитать количество строк без пробелов в файле
count_strings_without_spaces_in_file(FilePath, Count):- read_file_strings_in_list(FilePath, StringList), count_strings_without_spaces(StringList, Count).

% count_a_in_string(+String, -Result) - записать в Result кол-во символов A в строке
count_a_in_string(String, Result):- count_a_in_string(String, 0, Result), !.
count_a_in_string([], Result, Result):- !.
count_a_in_string([Symbol|TailString], CurCount, Result):- 
    char_code(Symbol, SymbolCode), 
    SymbolCode is 65, 
    NewCount is CurCount + 1, 
    count_a_in_string(TailString, NewCount, Result), !.
count_a_in_string([_|TailString], CurCount, Result):- count_a_in_string(TailString, CurCount, Result), !.

% count_mean_a_in_list_strings(+ListStrings, -Result) - записать в Result среднее количество символов A на строку в файле
count_mean_a_in_list_strings(ListStrings, Result):- count_mean_a_in_list_strings(ListStrings, 0, 0, Result), !.
count_mean_a_in_list_strings([], 0, _, Result):- Result is 0, !.
count_mean_a_in_list_strings([], CountOfStrings, CountOfA, Result):- Result is CountOfA/CountOfStrings, !.
count_mean_a_in_list_strings([String|TailStrings], CountOfStrings, CountOfA, Result):- 
    count_a_in_string(String, ThisCountA),
    NewCountA is CountOfA + ThisCountA,
    NewCountOfStrings is CountOfStrings + 1,
    count_mean_a_in_list_strings(TailStrings, NewCountOfStrings, NewCountA, Result), !.

% print_more_count_a(+StringList, +CountA) - напечатать все строки, в которых количество символов А больше, чем переданный параметр CountA.
print_more_count_a([], _):- !.
print_more_count_a([String|TailStrings], CountA):- 
    count_a_in_string(String, ThisCountA), 
    ThisCountA > CountA, 
    write_list_str(String),
    nl,
    print_more_count_a(TailStrings, CountA), !.
print_more_count_a([_|TailStrings], CountA):- print_more_count_a(TailStrings, CountA), !.

% print_more_mean_a_in_file(+FilePath) - напечатать все строки, в которых количество символов А больше, чем среднее кол-во этого символа в строке
print_more_mean_a_in_file(FilePath):- 
    read_file_strings_in_list(FilePath, StringList),
    count_mean_a_in_list_strings(StringList, MeanA),
    print_more_count_a(StringList, MeanA), !.