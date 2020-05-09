package org.jzy.clac;

import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * 计算器处理逻辑
 *
 * @author JZY
 */
public class CalcService {
	/**
	 * 按键处理逻辑
	 *
	 * @param e 事件对象
	 */
	public static void actionPerformed(ActionEvent e) {
		// 获取被点击的按钮
		JButton source = (JButton) e.getSource();
		// 获取按钮上的文字
		String name = source.getText();
		// 获取文本框里的文字
		JTextField jTextField = (JTextField) CalcFrame.getInstance().getContentPane().getComponent(0);
		String expr = jTextField.getText();

		// 各个按钮不同的处理逻辑
		switch (name) {
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "0":
				// 数字按钮：不能跟在右括号后边
				if (!expr.endsWith(")")) {
					// 禁止除0
					if (expr.endsWith("/") && "0".equals(name)) {
						System.out.println("禁止除0");
					} else {
						expr += name;
					}
				}
				break;
			case ".":
				// 小数点按钮：数字后，且一个数字只能有一个小数点
				if (expr.matches("^.*\\d$") && !expr.matches("^.*\\d\\.\\d$")) {
					expr += name;
				}
				break;
			case "-":
				// 减号：空、数字和左右括号后可输入
				if (expr.isEmpty() || expr.matches("^.*[\\d()]$")) {
					expr += name;
				}
				break;
			case "+":
			case "*":
			case "/":
				// 四则按钮（减号单独处理）：数字和右括号结尾可以写
				if (expr.matches("^.*[\\d,)]$")) {
					expr += name;
				}
				break;
			case "=":
				// 等号计算：数字或者右括号结尾，且左右括号数量相同
				if (expr.matches("^.*[\\d,)]$")) {
					int left = expr.replaceAll("[^(]", "").length();
					int right = expr.replaceAll("[^)]", "").length();
					if (left == right) {
						expr = String.valueOf(parse(expr));
						// 如果是整数，则不需要加小数位
						if (expr.endsWith(".0")) {
							expr = expr.replace(".0", "");
						}
					}
				}
				break;
			case "C":
				// 清空按钮
				expr = "";
				break;
			case "X":
				// 回退按钮
				if (!expr.isEmpty()) {
					expr = expr.substring(0, expr.length() - 1);
				}
				break;
			case "(":
				// 左括号按钮：刚开始或者四则和左括号结尾
				if (expr.isEmpty() || expr.matches("^.*[+\\-*/(]$")) {
					expr += name;
				}
				break;
			case ")":
				// 右括号按钮：数字或者右括号结尾，并且左括号比右括号多
				if (expr.matches("^.*\\d$") || expr.endsWith(")")) {
					int left = expr.replaceAll("[^(]", "").length();
					int right = expr.replaceAll("[^)]", "").length();
					if (left > right) {
						expr += name;
					}
				}
				break;
			default:
				break;
		}
		jTextField.setText(expr);
	}


	/**
	 * 递归计算表达式的值
	 * 括号的处理：如果有括号，先计算括号里的，然后把括号里的替换成计算结果。
	 *
	 * @param expr 表达式
	 * @return 计算结果
	 */
	private static double parse(String expr) {
		int index;
		// 去括号
		index = expr.indexOf("(");
		if (index >= 0) {
			int right = index, i = 0;
			while (true) {
				char c = expr.charAt(right);
				if (c == '(') {
					i++;
				} else if (c == ')') {
					i--;
					if (i == 0) {
						break;
					}
				}
				right++;
			}
			double temp = parse(expr.substring(index + 1, right));
			expr = expr.substring(0, index) + temp + expr.substring(right + 1);
			return parse(expr);
		}
		// 加法
		index = expr.indexOf("+");
		if (index >= 0) {
			return parse(expr.substring(0, index)) + parse(expr.substring(index + 1));
		}
		// 减法
		index = expr.lastIndexOf("-");
		if (index >= 0) {
			return parse(expr.substring(0, index)) - parse(expr.substring(index + 1));
		}
		// 乘法
		index = expr.indexOf("*");
		if (index >= 0) {
			return parse(expr.substring(0, index)) * parse(expr.substring(index + 1));
		}
		// 除法
		index = expr.lastIndexOf("/");
		if (index >= 0) {
			return parse(expr.substring(0, index)) / parse(expr.substring(index + 1));
		}
		// 数字
		if (expr.isEmpty()) {
			expr = "0";
		}
		return Double.parseDouble(expr);
	}
}
