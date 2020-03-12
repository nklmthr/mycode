package com.nklmthr.practice.phase5;

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG1 {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			String pointStr = sc.nextLine();
			String pointsStrArr[] = pointStr.split(",");
			int [] points = new int[]{3,1};//new int[]{Integer.parseInt(pointsStrArr[0]), Integer.parseInt(pointsStrArr[1])};
			System.out.println(findPaths(points));
			
		}
	}
	
	private static int findPaths(int[] points){
	    if(points[0]==0 && points[1]==0){
	    	System.out.println("1"+points[0]+","+points[1]);
	        return 0;
	    }
	    if(points[0]==0 && points[1]> 0){
	        points[1]--;
	        System.out.println("2"+points[0]+","+points[1]);
	        return findPaths(points) + 1;
	    }
	    if(points[1]==0 && points[0]> 0){
	        points[0]--;
	        System.out.println("3"+points[0]+","+points[1]);
	        return findPaths(points) + 1;
	    }
	     if(points[0]>0 && points[1]> 0){
	    	 System.out.println("4"+points[0]+","+points[1]);
	        return findPaths(new int[]{points[0]-1,points[1]}) +findPaths(new int[]{points[0],points[1]-1}) ;
	    }
	    return 0;   
	}
}