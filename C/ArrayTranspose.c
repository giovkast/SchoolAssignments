/* 
 * trans.c - Matrix transpose B = A^T
 *
 * Each transpose function must have a prototype of the form:
 * void trans(int M, int N, int A[N][M], int B[M][N]);
 *
 * A transpose function is evaluated by counting the number of misses
 * on a 1KB direct mapped cache with a block size of 32 bytes.
 */ 
#include <stdio.h>
#include "cachelab.h"

int is_transpose(int M, int N, int A[N][M], int B[M][N]);

/* 
 * transpose_submit - This is the solution transpose function that you
 *     will be graded on for Part B of the assignment. Do not change
 *     the description string "Transpose submission", as the driver
 *     searches for that string to identify the transpose function to
 *     be graded. 
 */



/* 
 * You can define additional transpose functions below. We've defined
 * a simple one below to help you get started. 
 */ 

/* 
 * trans - A simple baseline transpose function, not optimized for the cache.
 */
char trans_desc[] = "Simple row-wise scan transpose";

void trans(int M, int N, int A[N][M], int B[M][N]) {
    int i, j, tmp;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; j++) {
            tmp = A[i][j];
            B[j][i] = tmp;
        }
    }
}

char transpose_submit_desc[] = "Transpose submission";
void transpose_submit(int M, int N, int A[N][M], int B[M][N])
{
	int bRow, bCol, row, col;
	int diagonal = 0;
	int temp = 0;
	// case for 32x32 matrix
	if(N == 32 && M == 32)
	{
		for(bRow = 0; bRow < N; bRow += 8) {
			for(bCol = 0; bCol < M; bCol += 8) {
				for (row = bRow; row < bRow + 8; row++) {
					for (col = bCol; col < bCol + 8; col++) {
						if(row != col) {
							B[col][row] = A[row][col];
						} else {
							temp = A[row][col];
							diagonal = row;
						}
					}
					if(bRow == bCol) {
						B[diagonal][diagonal] = temp;
					}
				}  
			}
		}
	} 
	// case for 64x64 matrix
	else if(N  == 64 && M == 64) 
	{
		int r1,r2,r3,r4;
		int counter;
		for(bRow = 0; bRow < N; bRow += 8) {
			for(bCol = 0; bCol < M; bCol += 8) {
				for (counter = 0; counter < 8; counter++){
					r1 = A[bCol+counter][bRow];
					r2 = A[bCol+counter][bRow+1];
					r3 = A[bCol+counter][bRow+2];
					r4 = A[bCol+counter][bRow+3];
					B[bRow][bCol+counter] = r1;
					B[bRow+1][bCol+counter] = r2;
					B[bRow+2][bCol+counter] = r3;
					B[bRow+3][bCol+counter] = r4;
				}
				for (counter = 7; counter > -1; counter--) {
					r1 = A[bCol+counter][bRow+4];
					r2 = A[bCol+counter][bRow+5];
					r3 = A[bCol+counter][bRow+6];
					r4 = A[bCol+counter][bRow+7];
					B[bRow+4][bCol+counter] = r1;
					B[bRow+5][bCol+counter] = r2;
					B[bRow+6][bCol+counter] = r3;
					B[bRow+7][bCol+counter] = r4;
				}
			}
		}
	}
	// case for 61x67 matrix
	else if(N == 61 && M == 67) 
	{
		for(bRow = 0; bRow < N; bRow += 18) {
			for(bCol = 0; bCol < M; bCol += 18) {
				for (row = bRow; (row < bRow + 18) && (row < N); row++) {
					for (col = bCol; (col < bCol + 18) && (col < M); col++) {
						if(row != col) {
							B[col][row] = A[row][col];
						} else {
							temp = A[row][col];
							diagonal = row;
						}
					}
					if(bRow == bCol) {
						B[diagonal][diagonal] = temp;
					}
				}  
			}
		}
	}
}	


/*
 * registerFunctions - This function registers your transpose
 *     functions with the driver.  At runtime, the driver will
 *     evaluate each of the registered functions and summarize their
 *     performance. This is a handy way to experiment with different
 *     transpose strategies.
 */
void registerFunctions() {
    /* Register your solution function */
    registerTransFunction(transpose_submit, transpose_submit_desc); 

    /* Register any additional transpose functions */
    registerTransFunction(trans, trans_desc); 

}

/* 
 * is_transpose - This helper function checks if B is the transpose of
 *     A. You can check the correctness of your transpose by calling
 *     it before returning from the transpose function.
 */
int is_transpose(int M, int N, int A[N][M], int B[M][N]) {
    int i, j;
    for (i = 0; i < N; i++) {
        for (j = 0; j < M; ++j) {
            if (A[i][j] != B[j][i]) {
                return 0;
            }
        }
    }
    return 1;
}


