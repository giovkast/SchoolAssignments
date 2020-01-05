/* Question 1*/

:- op(100, yfx, plink),
	op(200, xfy, plonk).

/* 
	* A new operator is created with precedence 100 called plink, and is left-associative
	* Also another one with precendence 200 called plonk, and is right-associative
*/

/* b1)

	it returns X = tiger plink dog, Y = fish. Not X = tiger, Y = dog plink fish
	because the operator is dfined with yfx, therefor the Atoms after plink a 
	precedence of 100

	b2)

	It will return false, this means that (X plink Y) not equal cow plonk elephant plink bird.
	This is because plink has lower precedence than plonk.
	

 	b3)
	It will return X = lion plink tiger plonk horse plin donkey, because this
	equals (lion plink tiger) plonk (horse plink donkey), because plink has lower precedence 
	than plonk.
	

*/
% c)
pp_analyse(X plink Y):- 
	write('Principal operator: plink'), 
	nl, 
	write('Left sub-term:'),
	write(X), 
	nl,
	write('Right sub-term:'),
	write(Y). 

	pp_analyse(X plonk Y):- 
	write('Principal operator: plonk'), 
	nl, 
	write('Left sub-term:'), 
	write(X),
	nl,
	write('Right sub-term:'), 
	write(Y).


/* Question 2


:- op(100, fx, the),
   op(100, fx, a),
   op(200, xfx, has).

 a)

 claudia has (a car), with has as the prinicipal functor.

 b)

  Who = the lion
  What = hunger

	"The" is a prefix operator with the lowest precedence, therefor
	'The Lion' has hunger is returned. This matches with the Who has What
	if 'the lion' becomes who and 'hunger' becomes what.

 c)
	'X = she has whatever has style', looks like A = B = C which gives a 
	syntax error, because prolog doesn't know which operator has precedence,
	because their precedence value is the same.


*/

% 3

% this predicate will execute the move is execute/5
% move(north, (3,5), (X,Y)).
% move(south, (3,5), (X,Y)).

move(north, (X,Y), (X, Newy)) :-
	Newy is Y + 1.

move(south, (X,Y), (X, Newy)) :-
	Newy is Y - 1.

move(east, (X, Y), (Newx, Y)) :-
	Newx is X + 1.

move(west, (X, Y), (Newx, Y)) :-
	Newx is X - 1.

% above works !!!

% These facts will execute the turn is execute/5

turn(north, left, west).
turn(north, right, east).

turn(east, left, north).
turn(east, right, south).

turn(south, left, east).
turn(south, right, west).

turn(west, left, south).
turn(west, right, north).

% execute/5
% when execute is called with move, the move predicate will be called
% will make the correct move in the right direction.

execute((X,Y), CurrOrientation, move, (Newx, Newy), CurrOrientation) :-
	move(CurrOrientation, (X,Y), (Newx, Newy)).


% when execute is called with either left or right, the the fact: 'turn'
% is called which will rotate the robot in the right direction.

execute((X,Y), CurrOrientation, left, (X, Y), Neworientation) :-
	turn(CurrOrientation, left, Neworientation).

execute((X,Y), CurrOrientation, right, (X, Y), Neworientation) :-
	turn(CurrOrientation, right, Neworientation).

% status/5

%base case
status((X,Y), Curror, [], (X,Y), Curror).

% this predicate will recursively execute all the moves in the given list.
% Each iteration it will execute the move in the head of the list, and does this
% till the base case is reached.

status((X,Y), Curror, [H | T], (Finx, Finy), Finorr) :-
	execute((X,Y), Curror, H, (NextX, NextY), Nextor),
	status((NextX, NextY), Nextor, T, (Finx, Finy), Finorr).

% status/3

% status/3 will call status/5, in status/5 all the hard work will be done.

status(X, Position, Orientation) :- status((0,0), north, X, Position, Orientation).


% 4

prime(X) :- prime(X, 2).

prime(X, X).

prime(X, Y) :-
	X1 is X mod Y,
	\+ X1 =:= 0,
	Y1 is Y + 1,
	prime(X, Y1).



% 5

:- consult('words.pl'),
	write('words.pl succesfully consulted').

% atom_chars to decompose atom into list of chars.

word_letters(Atom, List) :-
	atom_chars(Atom, List).	


% this predicate will recursively check all elements of a list
% and compare the first argument with the element of the list

check_el(H, [H|_]).

check_el(X, [_| T]) :-
	check_el(X , T).

% base case
cover([], _).

% cover([a,e,i,o], [m,o,n,k,e,y,b,r,a,i,n])
% cover([e,e,l], [h,e,l,l,o]).


cover([H| T], List) :-
	Templist = List,	
	check_el(H, Templist),
	select(H, List, Newlist),
	cover(T, Newlist).

% solution/3

solution(List, Word, Length) :-
	word(Word),
	word_letters(Word, Dlist),
	length(Dlist, Length),
	cover(Dlist, List).


% topsolution/3

%base case
% topsolution([g,i,g,c,n,o,a,s,t], Word, 8).
% solution([g,i,g,c,n,o,a,s,t], Word, 8).

topsolution(X, Word, Score) :-
	length(X, Bla),
	findsol(X, Word,Bla, Score).


% predicate to lower the score

findsol(X, Word, Bla, Bla) :-
	solution(X, Word, Bla).

findsol(X, Word, Bla, Score) :-
	B is Bla - 1,
	findsol(X, Word, B, Score).









% 3.3

fibonacci(0,1).
fibonacci(1, 1).

fibonacci(X, Y) :-
	X1 is X - 1,
	X2 is X - 2,
	fibonacci(X1, Y1),
	fibonacci(X2, Y2),
	Y is Y1 + Y2.



fib(0, A, _, A).
fib(N, A, B, F) :- N1 is N - 1, Sum is A + B, fib(N1, B, Sum, F).
fib(N, F) :- fib(N, 0, 1, F).
















































 
