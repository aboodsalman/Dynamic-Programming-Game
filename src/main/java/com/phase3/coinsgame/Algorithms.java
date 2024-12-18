package com.phase3.coinsgame;

public class Algorithms {
    public static int coins[]; // The coins of the game
    /* Two dimensional array for dynamic programming approach, the first index specifies the left index
    that a player can choose, while the second index specifies the right index that a player can choose*/
    private static int dp[][];
    /* two dimensional array to specify if the player chose the front index or not, the value
    firstCoinPicked[left][right]=1 if the left coin is picked, 0 otherwise. This array is used to keep
    track with the chosen elements for every player */
    private static byte firstCoinPicked[][];

    public Algorithms() {}

    public static void findMaxCoins(){
        int n=coins.length;
        dp = new int[n][n];
        firstCoinPicked = new byte[n][n];
        /* when there's only one coin, the player have no option other than taking it, so dp[i][i]=coins[i]
        for all i<n */
        for(int i = 0; i < n; i++){
            dp[i][i] = coins[i];
        }
        // When there is only two coins, pick the max of them
        for(int i=0; i<n-1; i++){
            dp[i][i+1]=Math.max(coins[i], coins[i+1]);
            if(coins[i]>coins[i+1]){
                firstCoinPicked[i][i+1] = 1;
            } else{
                firstCoinPicked[i][i+1] = 0;
            }
        }

        for(int length=3; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int pickLeft = coins[i]+Math.min(dp[i + 2][i + length - 1], dp[i + 1][i + length - 2]);
                int pickRight = coins[i + length - 1]+Math.min(dp[i][i + length - 3], dp[i + 1][i + length - 2]);
                if (pickLeft > pickRight)
                    firstCoinPicked[i][i + length - 1] = 1; // the left side coin is chosen
                else if(pickLeft < pickRight)              // the right side coins is chosen
                    firstCoinPicked[i][i + length - 1] = 0;
                else{                // if choosing any coin gave the same result, choose the maximum one
                    if(coins[i]>=coins[i + length - 1])
                        firstCoinPicked[i][i + length - 1] = 1;
                    else
                        firstCoinPicked[i][i + length - 1] = 0;
                }
                dp[i][i + length - 1] = Math.max(pickLeft, pickRight); // the maximum possible sum of coins
            }
        }
    }
    public static byte[][] getFirstCoinPicked(){
        return firstCoinPicked;
    }
    public static int[][] getDp(){
        return dp;
    }
}
