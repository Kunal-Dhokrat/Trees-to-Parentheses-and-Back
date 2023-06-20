class Tree
{
	public Tree getLeft()
	{
		return null;
	}

	public Tree getRight() 
	{
		return null;
	}
}

class Leaf extends Tree 
{

}

class Branch extends Tree 
{
	protected Tree left;
	protected Tree right;

	public Branch(Tree left, Tree right)
	{
		this.left = left;
		this.right = right;
	}

	public Tree getLeft() 
	{
		return left;
	}

	public Tree getRight() 
	{
		return right;
	}
}

public class Main
{
	public static String treeToParens(Tree tree)
	{
		if (tree instanceof Leaf) 
		{
			return "";

		} 
		else if (tree instanceof Branch) 
		{
			Branch branch = (Branch) tree;

			return "(" + treeToParens(branch.getLeft()) + treeToParens(branch.getRight()) + ")";
		}
		return "";
	}

	public static Tree parensToTree(String parens)
	{
		java.util.Stack<Tree> stack = new java.util.Stack<>();
		Tree current = null;

		for (int i = 0; i < parens.length(); i++) 
		{
			char ch = parens.charAt(i);

			if (ch == '(') 
			{
				if (current != null)
				{
					Branch newNode = new Branch(null, null);
					stack.push(current);
					stack.push(newNode);
					current = newNode;
				} 
				else 
				{
					current = new Branch(null, null);
					stack.push(current);
				}
			} 
			else if (ch == ')') 
			{
				Tree node = stack.pop();
				Tree parent = stack.peek();

				if (parent instanceof Branch) 
				{
					Branch branchParent = (Branch) parent;

					if (branchParent.getLeft() == null) 
					{
						branchParent.left = node;
					} 
					else 
					{
						branchParent.right = node;
						current = branchParent;
					}
				}
				else
				{
					current = node;
				}
			}
		}

		return current;
	}

	public static void main(String[] args) 
	{
		Tree tree = new Branch(new Leaf(), new Branch(new Leaf(), new Leaf()));
		String parens = treeToParens(tree);
		System.out.println(parens); // Output: "(()())"

		Tree reconstructedTree = parensToTree(parens);
		System.out.println(reconstructedTree instanceof Branch); // Output: true
		System.out.println(reconstructedTree.getLeft() instanceof Leaf); // Output: true
		System.out.println(reconstructedTree.getRight() instanceof Branch); // Output: true
		System.out.println(reconstructedTree.getRight().getLeft() instanceof Leaf); // Output: true
		System.out.println(reconstructedTree.getRight().getRight() instanceof Leaf); // Output: true
	}
}
