:- consult(beta_convert).

% Giovanni Kastanja
% 6-05-2015
% Groep D
% HW 4

% Operators for logical connectives:
:- op(500, xfy, &).
:- op(510, xfy, =>).
% Operator for functional application:
:- op(400, yfx, *).


% Clauses for easy querying:

interpret(Sentence, Sem) :- s(Sem,Sentence, []).

logic(Sentence, Logic) :-
	interpret(Sentence, Sem),
	convert(Sem, Logic).


% a)
% Phrase structure rules:
s(SemNP*SemVP) --> np(SemNP), vp(SemVP).

np(SemD*SemN) --> d(SemD), n(SemN).
np(SemPN) --> pn(SemPN).


np(SemD*SemNom) --> d(SemD), nom(SemNom).
nom(SemA*SemNom) --> a(SemA), nom(SemNom).
nom(SemN) --> n(SemN).


vp(SemV*SemNP) --> v(SemV), np(SemNP).

% Pre-terminal rules:
n(Sem) --> [Word], {n(Word, Sem)}.
d(Sem) --> [Word], {d(Word, Sem)}.
v(Sem) --> [Word], {v(Word, Sem)}.
pn(Sem) --> [Word], {pn(Word, Sem)}.
a(Sem) --> [Word], {a(Word, Sem)}.

% Lexicon:
d(a, U^V^exists(X, U*X & V*X)).
d(every, U^V^forall(X, U*X => V*X)).

n(restaurant, X^restaurant(X)).
n(menu, X^menu(X)).

pn(albert, V^(V*a)).

v(has, U^Z^(U*Y^has(Z,Y))).
v(opens, U^Z^(U*Y^opens(Z,Y))).

a(vegetarian, U^X^(U*X & vegetarian(X))).
a(new, U^X^(U*X & new(X))).

