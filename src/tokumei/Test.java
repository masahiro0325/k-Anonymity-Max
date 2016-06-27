package tokumei;


public class Test {


	public static void main(String[] args) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		//new Database(4,100,"./src/tokumei/data.txt");

		//Input input = new Input("./src/tokumei/data.txt");
		Input input = new Input("./data/rand_100.txt");

		long start = System.currentTimeMillis();

		//計測したい処理を記述

		DataList inputData = new DataList(input.getList());
		DataTree tree = new DataTree(inputData,13);
		/* 10分以内に収まるのは1匿名と12匿名以上
		 *
		 */


		long end = System.currentTimeMillis();
		System.out.println((end - start)  + "ms");
		System.out.println("有用度："+tree.outputDataList().getTokumei());

		new Output(tree.outputDataList(),"./src/tokumei/result.txt");
	}

}
