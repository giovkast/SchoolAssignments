
x = [1, 2, 3].
y = [A, B, C].

append/concantenate(x ,y, Result).
append/concantenate([Element|x], y, [Element|xy] :-
append/concantenate(x, y, xy).
sort(xy).


var L1 = [1,2,3,4,5,6,7,8,9].
?- split([L1],4,L2,L3).