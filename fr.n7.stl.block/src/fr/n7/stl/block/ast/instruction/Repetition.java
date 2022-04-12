/**
 * 
 */
package fr.n7.stl.block.ast.instruction;

import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.SemanticsUndefinedException;
import fr.n7.stl.block.ast.expression.Expression;
import fr.n7.stl.block.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.scope.Scope;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a repetition instruction.
 * @author Marc Pantel
 *
 */
public class Repetition implements Instruction {

	private static int nbIteration;
	protected Expression condition;
	protected Block body;

	public Repetition(Expression _condition, Block _body) {
		this.condition = _condition;
		this.body = _body;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "while (" + this.condition + ") " + this.body;
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
		if (this.condition.collectAndBackwardResolve(_scope)) {
			return this.body.collect(_scope);
		} else {
			return false;
		}	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
		if (this.condition.fullResolve(_scope)) {
			return this.body.resolve(_scope);
		} else {
			return false;
		}	
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		Boolean conditionOk = this.condition.getType().compatibleWith(AtomicType.BooleanType);
		Boolean blockOk = this.body.checkType();
		return conditionOk && blockOk;
	}

     @Override
     public Type getReturnType() {
		return this.body.getReturnType();
     }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		this.body.allocateMemory(_register, _offset);
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment frag = _factory.createFragment();
		int nb = Repetition.nbIteration++;
		frag.append(this.condition.getCode(_factory));
		if (this.condition instanceof AccessibleExpression) {
			frag.add(_factory.createLoadI(this.condition.getType().length()));
		}
		frag.addPrefix("etiq_cond_tantque_" + nb);
		frag.add(_factory.createJumpIf("fin_tantQue_" + nb, 0));
		frag.append(this.body.getCode(_factory));
		frag.add(_factory.createJump("etiq_cond_tantque_" + nb));
		frag.addSuffix("fin_tantQue_" + nb);
		return frag;
	}

}
