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


% max_in_list(+List, -Result) - найти максимальное число в списке
max_in_list(List, Result):- max_in_list(List, CurRes, Result).
max_in_list([], CurRes, Result):- Result is CurRes, !.
max_in_list([HeadList|TailList], CurRes, Result):- var(CurRes), CurRes is HeadList, max_in_list(TailList, CurRes, Result), !.
max_in_list([HeadList|TailList],CurRes, Result):- HeadList > CurRes, max_in_list(TailList, HeadList, Result), !.
max_in_list([_|TailList], CurRes, Result):- max_in_list(TailList, CurRes, Result), !.

% check_max_in_interval/0 - проверить наличие максимального элемента списка в интервале
check_max_in_interval:-
    read_list_and_interval(List, From, To),
    max_in_list(List, Max),
    check_max_in_interval(Max, From, To, Result),
    print_result(Result).

% check_max_in_interval(+Max, +From, +To, -Result) - входит ли Max в интервал [From; To]
check_max_in_interval(Max, From, To, Result):-
    check_interval(Max, From, To),
    Result = true,
    !.

check_max_in_interval(_, _, _, Result):-
    Result = false,
    !.

%in_list(?List, ?El) - если El унифицирована, то проверить, входит ли данный элемент в массив
% если El не унифицирован, будут возвращать все элементы по очереди, до точки.
% если List не унифицирован, будет возвращён список с El.
in_list([El|_], El).
in_list([_|T], El):-in_list(T, El).

% Решение логической задачки.
% На заводе работали три друга: слесарь, токарь и сварщик. Их фамилии
% Борисов, Иванов и Семенов. У слесаря нет ни братьев, ни сестер. Он самый младший из
% друзей. Семенов, женатый на сестре Борисова, старше токаря. Назвать фамилии слесаря,
% токаря и сварщика.
% pr_friends/0
pr_friends:- 
    Friends = [_, _, _],
    in_list(Friends, [_, turner, _, _]),
    in_list(Friends, [_, locksmith, no_sister, youngest]),
    in_list(Friends, [_, welder, _, _]),

    in_list(Friends, [ivanov, _, _, _]),
    in_list(Friends, [semenov, _, zhenat, oldest]),
    in_list(Friends, [borisov, _, have_sister, _]),

    in_list(Friends, [_, turner, _, middle]),


    in_list(Friends, [ivanov, ProfIvanov, StatusIvanov, AgeIvanov]),
    in_list(Friends, [semenov, ProfSemenov, StatusSemenov, AgeSemenov]),
    in_list(Friends, [borisov, ProfBorisov, StatusBorisov, AgeBorisov]),
    write("ivanov:"),write(ProfIvanov),write(" "), nl,
    write("semenov:"),write(ProfSemenov),write(" "),nl,
    write("borisov:"),write(ProfBorisov), write(" "),nl,!.

% print_even_odd_elements/0 - напечатать сначала элементы, которые стоят на чётных индексах списка, а затем на нечётных
print_even_odd_elements:-
    write('Enter the list ("end" for end of list): '), read_list(List),
    print_even_elements(List),
    write(' '),
    print_odd_elements(List).

% print_even_elements(+List) - напечатать только элементы, стоящие на чётных индексах списка
print_even_elements([]):- !.
print_even_elements([X]):- write(X), !.
print_even_elements([X,_|T]) :-
    write(X),
    write(' '),
    print_even_elements(T).

% print_odd_elements(+List) - напечатать только элементы, стоящие на нечётных индексах списка
print_odd_elements([]):- !.
print_odd_elements([_]):- !.
print_odd_elements([_,X|T]) :-
    write(X),
    write(' '),
    print_odd_elements(T).

% check_alternating/0 - ввести и проверить список на чередование в нём вещественных и целых чисел
check_alternating:-
    write('Enter the list ("end" for end of list): '), read_list(List),
    !,
    check_alternating(List).

% check_alternating(+List) - предикат, проверяющий, что в списке чередуются вещественные и целые числа
check_alternating([_]):- !.
check_alternating([Int, Float|Tail]) :- 
    integer(Int), float(Float), check_alternating([Float|Tail]), !.
check_alternating([Float, Int|Tail]) :- 
    float(Float), integer(Int), check_alternating([Int|Tail]), !.

% count_elements/0 - ввести список и вывести в одном списке его уникальные элементы списка, а в втором - количество повторений каждого элемента
count_elements:-
    write('Enter the list ("end" for end of list): '), read_list(List),
    !,
    count_elements(List, L1, L2),
    write('Unique elements: '), write_list(L1),
    write('Count of elements: '), write_list(L2).
    
% count_elements(+List, -L1, -L2) - вывести в L1 уникальные элементы списка List, а в L2 количество повторений каждого элемента
count_elements([], [], []).

count_elements([X|Xs], L1, L2) :-
    count_elements(Xs, L1_Tail, L2_Tail),
    (   member(X, L1_Tail)
    ->  L1 = L1_Tail,
        nth1(Index, L1_Tail, X),
        nth1(Index, L2_Tail, Count),
        NewCount is Count + 1,
        replace(Index, L2_Tail, NewCount, L2)
    ;   L1 = [X|L1_Tail],
        L2 = [1|L2_Tail]
    ).

% replace(+Index, +CurList, +Value, -ResList) - заменить элемент на индексе Index в списке CurList значением Value и вернуть новый список в ResList
replace(1, [_|Xs], Value, [Value|Xs]).
replace(N, [X|Xs], Value, [X|Ys]) :-
    N > 1,
    N1 is N - 1,
    replace(N1, Xs, Value, Ys).

% is_prime(+X) - проверка числа на простоту
is_prime(X) :-
    X > 1,
    not(has_divisor(X, 2)).

% has_divisor(+X, +Y) - проверить, есть ли у X делители в диапазоне от Y до X
has_divisor(X, Y) :-
    Y * Y =< X,
    ( X mod Y =:= 0
    ; Y1 is Y + 1,
      has_divisor(X, Y1)
    ).

% prime_divisors/0
prime_divisors:-
    write('Enter the number: '), read(Number),
    !,
    prime_divisors(Number, Result),
    write('Divisors: '), write_list(Result).

% prime_divisors(+N, -Divisors) - получить список простых делителей числа, при чём, если есть делитель в степени, то выводить его несколько раз.
prime_divisors(N, Divisors) :-
    prime_divisors(N, 2, Divisors), !.

prime_divisors(1, _, []) :- !.

prime_divisors(N, D, [D|Divisors]) :-
    N mod D =:= 0,
    is_prime(D),
    N1 is N // D,
    prime_divisors(N1, D, Divisors), !.

prime_divisors(N, D, Divisors) :-
    D2 is D + 1,
    prime_divisors(N, D2, Divisors), !.

% sum_greater/0 - считать список и получить количество элементов из списка,которые больше, чем сумма всех предыдущих элементов списк
sum_greater:-
    write('Enter the list ("end" for end of list): '), read_list(List),
    !,
    sum_greater(List, Count),
    write("Count: "), write(Count).

% sum_greater(+List, -Count) - получить количество элементов из списка,которые больше, чем сумма всех предыдущих элементов списка
sum_greater(List, Count) :-
    sum_greater(List, 0, Count), !.

sum_greater([], _, 0).
sum_greater([X|Xs], PrevSum, Count) :-
    X > PrevSum,
    NewPrevSum is PrevSum + X,
    sum_greater(Xs, NewPrevSum, SubCount),
    Count is SubCount + 1, !.
sum_greater([X|Xs], PrevSum, Count) :-
    X =< PrevSum,
    sum_greater(Xs, PrevSum, Count), !.

% pr_student/0 - решение логической задачи про учеников факультетов
% Три друга – Петр, Роман и Сергей учатся на математическом, физическом и химическом факультетах университета.
% Если Петр математик, то Сергей не физик. Если Роман не физик, то Петр – математик.
% Если Сергей не математик, то Роман – химик. Где учится Роман?
pr_student:-
        Students=[_,_,_,_],
        in_list(Students,[petr, PetrFaculty]),
        in_list(Students,[roman, RomanFaculty]),
        in_list(Students,[sergey, SergeyFaculty]),

        in_list(Students,[_, mathematic]),
        in_list(Students,[_, physicist]),
        in_list(Students,[_, chemick]),

        (
            (PetrFaculty = mathematic, SergeyFaculty \= F);
            (RomanFaculty \= F, PetrFaculty = mathematic);
            (SergeyFaculty \= mathematic, RomanFaculty = chemick)
        ),

        write('Roman: '), write(RomanFaculty), !.
