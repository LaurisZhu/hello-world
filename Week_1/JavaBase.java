class JavaBase {
	public static void main(String[] args) {
		//验证变量超出范围的情况
		//byte b = (byte)128;
		//System.out.println(b);
		
		//对三元运算符双目提升的验证
		char a = 'a';
		int i = 96;
		//i为int，9.0为double，则i变为double,结果打印 96.0
		System.out.println(2==2 ? i : 9.0);
		//99为short或者byte型，'b'为char型，则99变为char型，结果打印c
		System.out.println(2==2 ? 99 : 'b');
		//两项范围相同则无提升，打印为200；
		System.out.println(2==2 ? 200 : 100);
		
		//对switch语句直通式语句(fallthrough)的验证
		int x = 3;
		
		switch (x){
			case 1:
				System.out.println(1);	
				break;
			case 2:
				System.out.println(2);
				//break;
			case 3:
				System.out.println(3);
				//break;
			default:
				System.out.println("erro!");
				//break;
			case 4:
				System.out.println(4);
				break;
	}
}