% read_str(-A) - Прочитать строку и записать в список A
read_str(A):- get0(X), r_str(X, A, []).

% r_str(+Code, +CurElement, -ResultString) - Прочитать элемент ReadElement и записать строку в список ResultString
r_str(10,A,A):-!. % встретили enter - конец строки
r_str(X,A,B):- append(B,[X],B1), get0(X1), r_str(X1,A,B1).

% del_1st(+InputList, -List) - Получить список List из InputList без первого элемента
del_1st([_|T], T):-!.

% delete_last(+InputList, -List) - Получить список List из InputList без последнего элемента
delete_last([_], []):-!.
delete_last([H|T], [H|Res]):-delete_last(T, Res), !.

% get_vertexes(-V) - Прочитать вершины и записать их в список V
get_vertexes(V):-write("Number of Vertexes"), nl, read(NumberOfVertexes), write("List of vertexes"), nl, N1 is NumberOfVertexes + 1, get_vertex(V1, N1), del_1st(V1, V).

% get_vertex(-VertexList, +N) - Получить список VertexList из N прочитанных вершин
get_vertex([], 0):-!.
get_vertex([H|T], N):-read_str(X), name(H, X), N1 is N - 1, get_vertex(T, N1), !.

% check_vertex(+VertexList, +V1) - Истина, если V1 присутствует в списке VertexList
check_vertex([V1|_], V1):- !.
check_vertex([_|T], V1):- check_vertex(T, V1).

% get_edges(+Vertexes, -Edges) - Получить список Edges прочитанных ребер для вершин Vertexes
get_edges(Vertexes, Edges):-read(EdgeCount), get0(X), get_edges(Vertexes, Edges, EdgeCount, 0).

% get_edges(+Vertexes, -Edges, +M, +CurrentM) - Получить список Edges из M прочитанных ребер для вершин Vertexes
get_edges(_, [], M, M):-!.
get_edges(Vertexes, [Edge|Tail], M, CurrentM):-get_edge(Vertexes, Edge), NewCurM is CurrentM + 1, get_edges(Vertexes, Tail, M, NewCurM), !.

% get_edge(+Vertexes, -Edge) - Прочитать ребро Edge для вершин Vertexes
get_edge(V, [V1, V2]):- write("EDGE"), nl, read_str(X), name(V1, X), check_vertex(V, V1), read_str(Y), name(V2, Y), check_vertex(V, V2).

% get_graph(-V, -E) - Получить вершины V и ребра E графа
get_graph(V, E):-get_vertexes(V), write("Count Edges"), nl, get_edges(V, E), !.

% in_list1(+InputList, +El) - Истина, если El присутствует в списке InputList
in_list1([El|_], El):-!.
in_list1([_|Tail], El):-in_list1(Tail, El), !.

% Задача 7.1


