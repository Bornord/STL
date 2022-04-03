/**
 * 
 */
package fr.n7.stl.block.ast.type;

import fr.n7.stl.block.ast.SemanticsUndefinedException;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;

/**
 * Implementation of the Abstract Syntax Tree node for a pointer type.
 * @author Marc Pantel
 *
 */
public class PointerType implements Type {

	protected Type element;

	public PointerType(Type _element) {
		this.element = _element;
	}
	
	public Type getPointedType() {
		return this.element;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		return this.element.equalsTo(_other);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		System.out.println(_other);
		System.out.println(this);
		return _other.compatibleWith(this.element);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		throw new SemanticsUndefinedException("Semantics merge undefined in PointerType.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#length(int)
	 */
	@Override
	public int length() {
		return 1+ this.element.length();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.element + " *)";
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.type.Type#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean resolve(HierarchicalScope<Declaration> _scope) {
		return this.element.resolve(_scope);
	}

}
