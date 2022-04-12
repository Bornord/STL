/**
 * 
 */
package fr.n7.stl.block.ast.expression;

import fr.n7.stl.block.ast.SemanticsUndefinedException;
import fr.n7.stl.block.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.block.ast.instruction.Conditional;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;
import fr.n7.stl.block.ast.type.AtomicType;
import fr.n7.stl.block.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Abstract Syntax Tree node for a conditional expression.
 * @author Marc Pantel
 *
 */
public class ConditionalExpression implements Expression {

	/**
	 * AST node for the expression whose value is the condition for the conditional expression.
	 */
	protected Expression condition;
	
	/**
	 * AST node for the expression whose value is the then parameter for the conditional expression.
	 */
	protected Expression thenExpression;
	
	/**
	 * AST node for the expression whose value is the else parameter for the conditional expression.
	 */
	protected Expression elseExpression;
	
	/**
	 * Builds a binary expression Abstract Syntax Tree node from the left and right sub-expressions
	 * and the binary operation.
	 * @param _left : Expression for the left parameter.
	 * @param _operator : Binary Operator.
	 * @param _right : Expression for the right parameter.
	 */
	public ConditionalExpression(Expression _condition, Expression _then, Expression _else) {
		this.condition = _condition;
		this.thenExpression = _then;
		this.elseExpression = _else;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean collectAndBackwardResolve(HierarchicalScope<Declaration> _scope) {
		if (this.condition.collectAndBackwardResolve(_scope)) {
			if (this.thenExpression==null && this.elseExpression != null){
				return this.elseExpression.collectAndBackwardResolve(_scope);
			} else if (this.elseExpression == null && this.thenExpression !=null) {
				return this.thenExpression.collectAndBackwardResolve(_scope);
			} else {
				return (this.thenExpression.collectAndBackwardResolve(_scope) && this.elseExpression.collectAndBackwardResolve(_scope));
			}
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#fullResolve(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean fullResolve(HierarchicalScope<Declaration> _scope) {
			Boolean condOk = this.condition.fullResolve(_scope);
			if (this.thenExpression==null && this.elseExpression != null){
				return condOk && this.elseExpression.fullResolve(_scope);
			} else if (this.elseExpression == null && this.thenExpression !=null) {
				return condOk && this.thenExpression.fullResolve(_scope);
			} else {
				return condOk && this.thenExpression.fullResolve(_scope) && this.elseExpression.fullResolve(_scope);
			}
		}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.condition + " ? " + this.thenExpression + " : " + this.elseExpression + ")";
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		 if (this.elseExpression.getType() == null) {
			 return this.thenExpression.getType();
		 } else {
			Type returnType = this.thenExpression.getType();
			if ( returnType == this.elseExpression.getType()) {
				return returnType;
			} else {
				return AtomicType.ErrorType;
			}
		 }
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment frag = this.condition.getCode(_factory);
		if (this.condition instanceof AccessibleExpression) {
			frag.add(_factory.createLoadI(this.condition.getType().length()));
		}
		int nb = Conditional.nbConditionnel++;
		frag.add(_factory.createJumpIf("elseBranch_" + nb, 0));
		frag.append(this.thenExpression.getCode(_factory));
		if (this.thenExpression instanceof AccessibleExpression) {
			frag.add(_factory.createLoadI(this.thenExpression.getType().length()));
		}
		frag.add(_factory.createJump("finIf_" + nb));
		frag.addSuffix("elseBranch_" + nb);
		Fragment frag_else = _factory.createFragment();
		frag_else.append(this.elseExpression.getCode(_factory));
		if (this.elseExpression instanceof AccessibleExpression) {
			frag.add(_factory.createLoadI(this.elseExpression.getType().length()));
		}
		frag.append(frag_else);
		frag.addSuffix("finIf_" + nb);
		frag.addComment(this.toString());
		return frag;
	}

}
