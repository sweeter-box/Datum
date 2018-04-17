/**
 * 
 */
package org.datum.application.olap;

import org.datum.expression.dialect.Dialect;

/**
 * 
 * 值对象用来描述事实表和维度表之间关联的条件.
 * 
 * @author 7cat
 * @see SqlCondition
 * @see CompareCondition
 */
public interface Condition{
	org.jooq.Condition asCondition(Dialect dialect);
}