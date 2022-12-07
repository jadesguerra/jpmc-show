package com.jpmc.show;

import java.util.Scanner;

import com.jpmc.show.views.HomeView;

public class ShowApplicationRunner {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		HomeView homeView = new HomeView(in);
		homeView.goToHome();
	}
}
