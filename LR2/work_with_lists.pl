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
