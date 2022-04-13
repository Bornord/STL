/**
 * 
 */
package fr.n7.stl.block.ast.instruction;

import java.util.Optional;
import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.SemanticsUndefinedException;
import fr.n7.stl.block.ast.expression.Expression;
import fr.n7.stl.block.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a conditional instruction.
 * @author Marc Pantel
 *
 */
public class Conditional implements Instruction {

	public static int nbConditionnel;
	protected Expression condition;
	protected Block thenBranch;
	protected Block elseBranch;

	public Conditional(Expression _condition, Block _then, Block _else) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = _else;
	}

	public Conditional(Expression _condition, Block _then) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "if (" + this.condition + " )" + this.thenBranch + ((this.elseBranch != null)?(" else " + this.elseBranch):"");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
		if (this.condition.collectAndBackwardResolve(_scope)) {
			if (this.thenBranch==null && this.elseBranch != null){
				return this.elseBranch.collect(_scope);
			} else if (this.elseBranch == null && this.thenBranch !=null) {
				return this.thenBranch.collect(_scope);
			} else {
				return (this.thenBranch.collect(_scope) && this.elseBranch.collect(_scope));
			}
		} else {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
			Boolean condOk = this.condition.fullResolve(_scope);
			if (this.thenBranch==null && this.elseBranch != null){
				return condOk && this.elseBranch.resolve(_scope);
			} else if (this.elseBranch == null && this.thenBranch !=null) {
				return condOk && this.thenBranch.resolve(_scope);
			} else {
				return condOk && this.thenBranch.resolve(_scope) && this.elseBranch.resolve(_scope);
			}
		}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		Boolean elseBranchOk = true;
		Boolean conditionOk = this.condition.getType().compatibleWith(AtomicType.BooleanType);
		Boolean thenBranchOk = this.thenBranch.checkType();
		if (this.elseBranch != null) {
			elseBranchOk = this.elseBranch.checkType();
		}
		return conditionOk && thenBranchOk && elseBranchOk;
	}

	@Override
	public Type getReturnType() {
		 if (this.elseBranch == null) {
			 return this.thenBranch.getReturnType();
		 } else {
			Type returnType = this.thenBranch.getReturnType();
			if ( returnType == this.elseBranch.getReturnType()) {
				return returnType;
			} else {
				return AtomicType.ErrorType;
			}
		 }
	}
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		this.thenBranch.allocateMemory(_register, _offset);
		if (this.elseBranch != null) { 
			this.elseBranch.allocateMemory(_register, _offset); 
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment frag = this.condition.getCode(_factory);
		if (this.condition instanceof AccessibleExpression) {
			frag.add(_factory.createLoadI(this.condition.getType().length()));
		}
		int nb = Conditional.nbConditionnel++;
		frag.add(_factory.createJumpIf("elseBranch_" + nb, 0));
		frag.append(this.thenBranch.getCode(_factory));
		frag.add(_factory.createJump("finIf_" + nb));
		frag.addSuffix("elseBranch_" + nb);
		Fragment elseFrag = _factory.createFragment();
		if (this.elseBranch != null) {
			elseFrag.append(this.elseBranch.getCode(_factory));
		}
		frag.append(elseFrag);
		frag.addSuffix("finIf_" + nb);
		return frag;
	}

}
