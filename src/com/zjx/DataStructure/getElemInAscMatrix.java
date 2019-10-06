package com.zjx.DataStructure;

public class getElemInAscMatrix {
    /*
    * 输入一个N*N数字矩阵并且已经读入内存.
    * 每一行从左往右递增.每一列则从上到下递增.给出一个O(N)最坏情形算法以决定数X是否在该矩阵中
    *
    */
    static boolean get(int[][] aa,int k){
        if (aa == null)
            return false;
        int row,column;
        column = row = aa.length - 1;     //非N阶矩阵时column = aa[0].length - 1;
        //一阶矩阵直接判断
        if (row == 1)
            return k == aa[0][0]? true:false;
        int x = 0,y = column;
        while (x <= row && y >= 0) {

            if (x == row){
                y = recursionBinarySearch(aa[x],k,0,y);
                System.out.println(k + "在矩阵中位置: (" + x + "," + y + ")");
                return true;
            }

            if (k == aa[x][y]) {
                System.out.println(k + "在矩阵中位置: (" + x + "," + y + ")");
                return true;
            }
            else if (k < aa[x][y])
                y--;
            else
                x++;
        }
        System.out.println("矩阵中未找到k");
        return false;
    }


    //递归方法进行二分查找
    public static int recursionBinarySearch(int[] arr,int key,int low,int high){
        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }
        int middle = (low + high)/2;
        if (arr[middle] < key)
            return recursionBinarySearch(arr,key,middle+1,high);
        else if (arr[middle] > key)
            return recursionBinarySearch(arr,key,0,middle-1);
        else
            return middle;
    }
    //非递归实现二分查找(while循环)
    /**
     * 不使用递归的二分查找
     *title:commonBinarySearch
     *@param arr
     *@param key
     *@return 关键字位置
     */
    public static int commonBinarySearch(int[] arr,int key){
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;			//定义middle

        if(key < arr[low] || key > arr[high] || low > high){
            return -1;
        }

        while(low <= high){
            middle = (low + high) / 2;
            if(arr[middle] > key){
                //比关键字大则关键字在左区域
                high = middle - 1;
            }else if(arr[middle] < key){
                //比关键字小则关键字在右区域
                low = middle + 1;
            }else{
                return middle;
            }
        }

        return -1;		//最后仍然没有找到，则返回-1
    }

    public static void main(String[] args) {
        int[][] aa = {{1,3,5,7},{11,13,15,17},{21,23,25,27},{31,33,35,37}};
        get(aa,33);
    }

}
