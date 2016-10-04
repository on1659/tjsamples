// import java.lang.*; // implicitly imported

class Main {
	public static void main(String[] args) {
		System.out.println("Run");

		new Constructor().run();
		
		// new Constructor.Base(); // error
		new Constructor().new Base();
		
		System.out.println("Static message: " + StaticSample.Static.msg);
		StaticSample.Static.hello_static();
		
		new OrderOfInstance();

		new Inherit().run();

		new ErrorHandling().run();
	}
}

/**
 * 
 *
 */
class Constructor {

	class Base {
		// default constructor implicited
		// public Base() {}
	}

	class Base2 {
		String name;
		// discarded default constructor
		public Base2(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}

	class Base3 {
		public Base3() {}
		public Base3(String name) {}
	}

	public void run() {
		Base base = new Base();
		// Base2 base2 = new Base2(); // compile error
		Base2 base2 = new Base2("hello");
		Base3 base3_1 = new Base3();
		Base3 base3_2 = new Base3("hello");
	}
}

/**
 * 
 *
 */
class StaticSample {
	public static class Static {
		public static String msg = "hello world";
		public static void hello_static() {
			System.out.println("hello_static()");
		}
	}
}

/**
 * 
 *
 */
class OrderOfInstance {

	public static int i_static;
	public int i;
	public int j = 2;
	public int x = j;

	static {
		System.out.println("static{}");
	}

	{
		System.out.println("{}");
	}
	
	public OrderOfInstance() {
		int num;
		System.out.println("Constructor - OrderOfInstance");
		System.out.printf(" - i(static): %d, i: %d\n", i_static, i);
		System.out.printf(" - j: %d, x: %d\n", j, x);
		// System.out.println("" + num); // compile error - num (local variable) is not initialized
	}
}

/**
 * 
 *
 */
class Classes {
	class Cls {
		public void hello() {
		}
	}

	interface Interface {
		public void hello();
	}

	abstract class AbsCls {
		public abstract void hello();
	}

	class ImplInterface implements Interface {
		public void hello() {
			// must be implemented
		}
	}

	class ImplAbsCls extends AbsCls {
		public void hello() {
			// must be implemented
		}
	}
}

/**
 * 
 *
 */
class Inherit {
	
	class Base {
		public String toString() {
			return "Base";
		}
	}

	class Child extends Base {
		public String toString() {
			return "Child";
		}
	}

	class User {
		private String name;
		private int age;
		public User() {
			System.out.println("User::constructor()");
		}
		public User(String name) {
			// default constructor call implicitly
			this.name = name;
		}
		public User(String name, int age) {
			this(name);
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String toString() {
			return "name: " + name + ", age: " + age;
		}
	}

	class UserWrapper extends User {
		private String description;
		public UserWrapper(String name, int age, String description) {
			super(name);
			this.description = description;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String toString() {
			return super.toString() + ", " + description;
		}

	}

	public void run() {
		Object obj = new Object();
		System.out.println("Object.toString() : " + obj.toString());

		Base base = new Base();
		System.out.println("Base.toString() : " + base.toString());

		base = new Child();
		System.out.println("Child.toString() : " + base.toString());

		User user = new User("kim", 26);
		System.out.println(user);
		UserWrapper userWrapper = new UserWrapper("lee", 30, "short description");
		System.out.println(userWrapper);
	}
}

/**
 * 
 *
 */
class ErrorHandling {

	public void run() {
		// Integer i = null;
		// i.toString(); // runtime error
		
		// bypassException(); // compile error - Exception must be handled

		try {
			bypassException();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("no problem~");
		}
	}

	public void bypassException() throws Exception {
		throw new Exception();
	}
}

