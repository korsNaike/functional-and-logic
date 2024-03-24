% max(+X, +Y, -Z) - найти максимальное среди 2 чисел
max(X, Y, X):- X>Y, !.
max(_, Y, Y).

% max3(+X, +Y, +U, -Z) - найти максимальное среди 3 чисел
max3(X, Y, U, X):- X>Y, X>U, !.
max3(_, Y, U, Y):- Y>U, !.
max3(_, _, U, U).

% fact(+X, -Y) - факториал вверх
fact(0,1):-!.
fact(N,X):-N1 is N-1,fact(N1,X1),X is N*X1.

% fact_down(+X, -Y) - факториал вниз
fact_down(N,X):-fact_down(0,1,N,X).
fact_down(N,Y,N,Y):-!.
fact_down(I,Y,N,X):-I1 is I+1, Y1 is Y*I1, fact_down(I1,Y1,N,X).

% sum_cifr(+X, -Y) - найти сумму цифр
sum_cifr(0, 0):- !.
sum_cifr(X, SummCifr):- X1 is X // 10, Ost is X mod 10, sum_cifr(X1, SummCifr1), SummCifr is SummCifr1 + Ost.

% sum_cifr_down(+X, -Y) - найти сумму цифр (рекурсией вниз)
sum_cifr_down(N, X):- sum_cifr(N, 0, X).
sum_cifr(0, X, X):- !.
sum_cifr(N, CurX, X):- N1 is N // 10, Ost is N mod 10, NewX is CurX + Ost, sum_cifr(N1, NewX, X).

% count_local_max(+List, -Count) - посчитать количество локальных максимумов
count_loc_max(List, Count):- count_loc_max(List, 0, Count).
count_loc_max([_, _], Count, Count):- !.
count_loc_max([A, B, C | Tail], CurCount, Count):- B>A, B>C, 
    NewCount is CurCount + 1, count_loc_max([B, C| Tail], NewCount, Count), !.
count_loc_max([_, B, C | Tail], CurCount, Count):- count_loc_maxx([B, C| Tail], CurCount, Count).

% free_from_square(+X) - проверить, свободное ли число от квадратов
free_from_square(X):- MaxDel is X - 1, free_from_square(2, MaxDel, X), !, true.
free_from_square(MaxDel, MaxDel, X):- !.
free_from_square(N, MaxDel, X):- Square is N * N, Ost is X mod Square, Ost \= 0, NextN is N + 1, free_from_square(NextN, MaxDel, X).

% read_list(-List, +N) - ввод списка с клавиатуры, N - количество элементов
read_list(List,N):-read_list(List,N,0,[]).
read_list(List,N,N,List):-!.
read_list(List,N,K,NewList):-read(X), append(NewList,[X],AppenListResult),K_next is K+1, read_list(List,N,K_next,AppenListResult).

% write_list(+List) - напечатать список
write_list([]):-!.
write_list([H|T]):-write(H), nl, write_list(T).

% sum_list_down(+List, ?Sum) - используя рекурсию вниз, найти сумму элементов списка или проверить, является ли Sum - суммой элементов списка
sum_list_down(List, Sum):- sum_list_down(List, 0, Sum).
sum_list_down([], CurSum, Sum):- Sum is CurSum, !.
sum_list_down([HeadList|TailList], CurSum, Sum):- NewCurSum is CurSum + HeadList, sum_list_down(TailList, NewCurSum, Sum).

% sum_list_up(+List, ?Sum) - используя рекурсию вверх, найти сумму элементов списка или проверить, является ли Sum - суммой элементов списка
sum_list_up([], 0):- !. 
sum_list_up([HeadList|TailList], Sum):- sum_list_up(TailList, NewSum), Sum is NewSum + HeadList.

% remove_with_sum_number(+List, +NeedSum) - удаляет из списка элементы, сумма цифр которых равна NeedSum
remove_with_sum_number(List, NeedSum):- remove_with_sum_number(List, NeedSum, []).
remove_with_sum_number([], _, ResultList):- write_list(ResultList), !.
remove_with_sum_number([HeadList|TailList], NeedSum, ResultList):- 
        sum_cifr_down(HeadList, SumNumbers), 
        NeedSum is SumNumbers,
        remove_with_sum_number(TailList, NeedSum, ResultList), !.
remove_with_sum_number([HeadList|TailList], NeedSum, ResultList):- append(ResultList, [HeadList], NewList), remove_with_sum_number(TailList, NeedSum, NewList), !.

% min_digit(+Number, -Min) - найти минимальную цифру в числе
min_digit(Number, Min):- min_digit(Number, CurMin, Min).
min_digit(0, CurMin, CurMin):- !. 
min_digit(Number, CurMin, Min):- var(CurMin), NewNumber is Number // 10, Ost is Number mod 10, min_digit(NewNumber, Ost, Min), !. 
min_digit(Number, CurMin, Min):- NewNumber is Number // 10, Ost is Number mod 10, Ost < CurMin, min_digit(NewNumber, Ost, Min), !.
min_digit(Number, CurMin, Min):- NewNumber is Number // 10, min_digit(NewNumber, CurMin, Min), !.

% proizv_cifr_ne_del_5(+N, -X) - найти произведение цифр числа, не делящихся на 5
proizv_cifr_ne_del_5(N, X):- proizv_cifr_ne_del_5(N, 1, X).
proizv_cifr_ne_del_5(0, X, X):- !.
proizv_cifr_ne_del_5(N, CurX, X):- N1 is N // 10, 
                                Digit is N mod 10, 
                                Ost is Digit mod 5, 
                                Ost \= 0,
                                NewX is CurX * Digit, 
                                proizv_cifr_ne_del_5(N1, NewX, X), !.
proizv_cifr_ne_del_5(N, CurX, X):- N1 is N // 10, proizv_cifr_ne_del_5(N1, CurX, X), !.

% nod(+FirstNumber, +SecondNumber, -Result) - найти НОД двух чисел
nod(FirstNumber, 0, FirstNumber):- !.
nod(_, 0, _):- !, fail.
nod(FirstNumber, SecondNumber, Result):- Ost is FirstNumber mod SecondNumber, nod(SecondNumber, Ost, Result).