/**
 * 
 */
package fr.n7.stl.block.ast.type;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.block.ast.SemanticsUndefinedException;
import fr.n7.stl.block.ast.scope.Declaration;
import fr.n7.stl.block.ast.scope.HierarchicalScope;

/**
 * Implementation of the Abstract Syntax Tree node for a function type.
 * @author Marc Pantel
 *
 */
public class FunctionType implements Type {

	private Type result;
	private List<Type> parameters;

	public FunctionType(Type _result, Iterable<Type> _parameters) {
		this.result = _result;
		this.parameters = new LinkedList<Type>();
		for (Type _type : _parameters) {
			this.parameters.add(_type);
		}
	}

	public Type getReturnType() {
		return this.result;
	}

	public List<Type> getParametersType() {
		return this.parameters;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		if (_other instanceof FunctionType) {
			FunctionType _local = (FunctionType) _other;
			if (this.result.equalsTo(_local.getReturnType())) {
				if (this.parameters.size() == _local.getParametersType().size()) {
					for (int i =0; i < this.parameters.size(); i++) {
						if (!(this.parameters.get(i).equalsTo(_local.getParametersType().get(i)))) {
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}	
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		if (_other instanceof FunctionType) {
			FunctionType _local = (FunctionType) _other;
			if (this.result.compatibleWith(_local.getReturnType())) {
				if (this.parameters.size() == _local.getParametersType().size()) {
					for (int i =0; i < this.parameters.size(); i++) {
						if (!(this.parameters.get(i).compatibleWith(_local.getParametersType().get(i)))) {
							return false;
						}
					}
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}	
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		throw new SemanticsUndefinedException( "merge is undefined in FunctionType.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#length(int)
	 */
	@Override
	public int length() {
		throw new SemanticsUndefinedException("Semantics length is undefined in FunctionType.");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String _result = "(";
		Iterator<Type> _iter = this.parameters.iterator();
		if (_iter.hasNext()) {
			_result += _iter.next();
			while (_iter.hasNext()) {
				_result += " ," + _iter.next();
			}
		}
		return _result + ") -> " + this.result;
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.type.Type#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean resolve(HierarchicalScope<Declaration> _scope) {
		throw new SemanticsUndefinedException("Semantics resolve is undefined in FunctionType.");
	}

    public Type getResultType() {
        return null;
    }

}
