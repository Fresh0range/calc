package org.jzy.clac;

import javax.swing.*;

import java.awt.*;

/**
 * 计算器窗口
 *
 * @author JZY
 */
public class CalcFrame extends JFrame {
	/** 单例 */
	private static CalcFrame singleton;

	/**
	 * 获取单例，因为不存在多线程安全问题，所以就不用DCL了。
	 *
	 * @return 单例
	 */
	public static CalcFrame getInstance() {
		if (singleton == null) {
			singleton = new CalcFrame();
		}
		return singleton;
	}

	/**
	 * 私有构造方法
	 *
	 * @throws HeadlessException
	 */
	private CalcFrame() throws HeadlessException {
		super();

		// 设置内容面板大小
		this.getContentPane().setPreferredSize(new Dimension(300, 300));
		// 设置内容面板布局器为null
		this.getContentPane().setLayout(null);

		JTextField jTextField = new JTextField();
		//设置文本不可编辑
		jTextField.setEditable(false);
		// 设置文本字体
		jTextField.setFont(new Font(null, 0, 20));
		// 设置文本框位置和大小
		jTextField.setBounds(new Rectangle(0, 0, 300, 50));
		// 设置文本对齐方式
		jTextField.setHorizontalAlignment(JTextField.RIGHT);
		this.getContentPane().add(jTextField);

		JPanel jPanel = new JPanel();
		// 设置按钮面板布局为5行4列的网格布局，布局如下：
        /*
            (   )   C   +
            7   8   9   -
            4   5   6   *
            1   2   3   /
            X   0   .   =
         */
		jPanel.setLayout(new GridLayout(5, 4));
		// 设置按钮面板的位置和大小
		jPanel.setBounds(new Rectangle(0, 50, 300, 250));
		this.getContentPane().add(jPanel);

		// 按钮
		JButton jButton1 = setButton(new JButton("1"));
		JButton jButton2 = setButton(new JButton("2"));
		JButton jButton3 = setButton(new JButton("3"));
		JButton jButton4 = setButton(new JButton("4"));
		JButton jButton5 = setButton(new JButton("5"));
		JButton jButton6 = setButton(new JButton("6"));
		JButton jButton7 = setButton(new JButton("7"));
		JButton jButton8 = setButton(new JButton("8"));
		JButton jButton9 = setButton(new JButton("9"));
		JButton jButton0 = setButton(new JButton("0"));
		JButton jButtonPoint = setButton(new JButton("."));
		JButton jButtonEqual = setButton(new JButton("="));
		JButton jButtonAdd = setButton(new JButton("+"));
		JButton jButtonSub = setButton(new JButton("-"));
		JButton jButtonMuti = setButton(new JButton("*"));
		JButton jButtonDiv = setButton(new JButton("/"));
		JButton jButtonCancel = setButton(new JButton("X"));
		JButton jButtonClear = setButton(new JButton("C"));
		JButton jButtonRight = setButton(new JButton(")"));
		JButton jButtonLeft = setButton(new JButton("("));

		// 按顺序添加
		jPanel.add(jButtonLeft);
		jPanel.add(jButtonRight);
		jPanel.add(jButtonClear);
		jPanel.add(jButtonAdd);
		jPanel.add(jButton7);
		jPanel.add(jButton8);
		jPanel.add(jButton9);
		jPanel.add(jButtonSub);
		jPanel.add(jButton4);
		jPanel.add(jButton5);
		jPanel.add(jButton6);
		jPanel.add(jButtonMuti);
		jPanel.add(jButton1);
		jPanel.add(jButton2);
		jPanel.add(jButton3);
		jPanel.add(jButtonDiv);
		jPanel.add(jButtonCancel);
		jPanel.add(jButtonPoint);
		jPanel.add(jButton0);
		jPanel.add(jButtonEqual);

		// 设置窗口标题
		this.setTitle("计算器");
		// 设置窗口不可调整
		this.setResizable(false);
		// 设置窗口的可见性必须放到最后，否则组件可能显示有问题
		this.setVisible(true);
		// 设置窗口大小自适应
		this.pack();
		// 设置窗口居中
		this.setLocationRelativeTo(null);
		// 关闭窗口时退出程序
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * 设置按钮的一些公共属性
	 *
	 * @param jButton 按钮
	 * @return 设置完成后的按钮
	 */
	public JButton setButton(JButton jButton) {
		// 设置字体大小
		jButton.setFont(new Font(null, 0, 20));
		// 点击时不显示内部的线
		jButton.setFocusPainted(false);
		// 设置按钮监听
		jButton.addActionListener(CalcService::actionPerformed);
		return jButton;
	}
}
