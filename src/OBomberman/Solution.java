package OBomberman;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */
    static int gridRows, gridColumns;
    public static List<String> bomberMan(int n, List<String> grid) {
    // Write your code here  
              
        if(n==1) return grid;
        
        gridRows = grid.size();
        gridColumns = grid.get(0).length();
        
        char[][][] bombMaps = new char[4][gridRows][];                
        char[] fullBombRow = new char[gridColumns];
        for(int j=0; j<gridColumns; j++){
            fullBombRow[j] = 'O';
        }
        
        
        //Initial bomb copy
        for(int i=0; i<gridRows; i++){
            bombMaps[0][i]=grid.get(i).toCharArray();
            
        }
        
        //Full Bomb In Map(1,2,3)
        for(int arrIdx=1; arrIdx<4; arrIdx++){
            for(int i=0; i<gridRows; i++){
                bombMaps[arrIdx][i] = fullBombRow.clone();
                //System.arraycopy(fullBombRow, 0, bombMaps[arrIdx][i], 0,gridColumns);
            }
        }
        
        //After Initial Bomb Explode
        explodeResult(bombMaps[0], bombMaps[3]);
        
        //After Second Bomb Explode
        explodeResult(bombMaps[3], bombMaps[1]);
        
        for(int i=0; i<gridRows; i++){
            bombMaps[0][i] = fullBombRow.clone();
            //System.arraycopy(fullBombRow, 0, bombMaps[arrIdx][i], 0,gridColumns);
        }
        return buildStringFrom2dArr(bombMaps[n%4]);
    }

    public static void explodeResult(char[][] planted, char[][] result){
        for(int i=0; i<gridRows; i++){
            for(int j=0; j<gridColumns; j++){
                //If a bomb is found, Process explode and get the result of Explosion
                if(planted[i][j]=='O')
                    processBombExplode(result,i,j);
            }
        }
        return;
    }
    
    public static void processBombExplode(char[][] result, int row, int col){
        //Coordinates of bomb and affected cells (UDLR).
        int[][] bombExplodeRange = {{row,col},{row,col+1},{row,col-1},{row+1,col},{row-1,col}};
        
        for(int i=0; i<bombExplodeRange.length; i++){
            int r = bombExplodeRange[i][0];
            int c = bombExplodeRange[i][1];
            //If r,c is in the Grid, Draw "Exploded" Cell
            if(r>=0 && r<gridRows
                && c>=0 && c<gridColumns){
                    result[r][c]='.';
            }
        }
        return;
    }
    
    // char 2d Array를 다시 String List로 변환한다.
    public static List<String> buildStringFrom2dArr(char[][] bombMap){
        List<String> resultMap = new ArrayList<>();
        for(int i=0;i<gridRows;i++){
            resultMap.add(
                String.valueOf(bombMap[i])
            );
        }
        return resultMap;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
