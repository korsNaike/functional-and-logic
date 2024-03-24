% read_list(-List) - предикат чтения списка с клавиатуры
read_list([HeadList|TailList]):-read(X), X \= end, HeadList is X, read_list(TailList).
read_list([]):-!.

% write_list(+List) - напечатать список
write_list([]):-!.
write_list([H|T]):-write(H), nl, write_list(T).

% read_list_and_index(-List, -Index) - Предикат чтения массива и индекса
read_list_and_index(List, Index) :-
    write('Enter the list ("end" for end of list): '), read_list(List),
    write('Enter the index: '), read(Index).

% is_global_max(+List, +Index, -Result):- проверить, является ли элемент из списка по индексу глобальным максимумом.
is_global_max(List, Index, Result) :-
    nth0(Index, List, Element),
    max_list(List, Max),
    Element =:= Max,
    Result = true.

is_global_max(List, Index, Result) :-
    nth0(Index, List, Element),
    max_list(List, Max),
    Element \= Max,
    Result = false.

% print_result(+Result) - вывод результата
print_result(Result) :-
    write('Result: '), write(Result).

% check_max_element_in_list/0 - предикат для определения, является ли элемент по указанному индексу, глобальным максимумом.
check_max_element_in_list :-
    read_list_and_index(List, Index),
    is_global_max(List, Index, Result),
    print_result(Result).

% read_list_and_interval(-List, -From, -To) - считать список и интервал с клавиатуры
read_list_and_interval(List, From, To):-
    write('Enter the list ("end" for end of list): '), read_list(List),
    write('Enter the beginning of the interval: '), read(From),
    write('Enter the end of the interval: '), read(To).

% check_interval(+Number, +From, +To) - проверить, входит ли число в интервал
check_interval(Number, From, To):- Number >= From, Number =< To.

% min_in_interval_in_list(+List, -Result) - найти минимальное число в списке
min_in_interval_in_list(List, From, To, Result):- min_in_interval_in_list(List, From, To, CurMin, 0, Result).

% когда список закончится, унифицируем Result
min_in_interval_in_list([], _, _, _, CurRes, Result):- Result is CurRes, !.

% вариант, если изначальное значение минимума не унифицировано
min_in_interval_in_list([Head|Tail], From, To, CurMin, _, Result):- 
    check_interval(Head, From, To),
    var(CurMin),
    CurMin is Head, 
    NewCurRes is 1, 
    min_in_interval_in_list(Tail, From, To, CurMin, NewCurRes, Result), !.

% если значение Head равно минимуму, то прибавляем к счётчику + 1
min_in_interval_in_list([Head|Tail], From, To, CurMin, CurRes, Result):-
    check_interval(Head, From, To),
    Head =:= CurMin,
    NewCurRes is CurRes + 1,
    min_in_interval_in_list(Tail, From, To, CurMin, NewCurRes, Result), !.

% если значение Head меньше минимума, то обновляем счётчик, ставим 1
min_in_interval_in_list([Head|Tail], From, To, CurMin, _, Result):-
    check_interval(Head, From, To),
    Head < CurMin,
    NewCurRes is 1,
    min_in_interval_in_list(Tail, From, To, Head, NewCurRes, Result), !.

% если ничего не подошло, то просто идём дальше по списку
min_in_interval_in_list([_|Tail], From, To, CurMin, CurRes, Result):- min_in_interval_in_list(Tail, From, To, CurMin, CurRes, Result), !.

% check_count_min_in_interval/0 - найти количество минимальных элементов на интервале внутри списка
check_count_min_in_interval:-
    read_list_and_interval(List, From, To),
    min_in_interval_in_list(List, From, To, Result),
    print_result(Result).