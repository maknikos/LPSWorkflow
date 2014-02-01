// Generated from LPS.g4 by ANTLR 4.x
package com.LPSWorkflow.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LPSParser}.
 */
public interface LPSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LPSParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull LPSParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull LPSParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#g}.
	 * @param ctx the parse tree
	 */
	void enterG(@NotNull LPSParser.GContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#g}.
	 * @param ctx the parse tree
	 */
	void exitG(@NotNull LPSParser.GContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#goals}.
	 * @param ctx the parse tree
	 */
	void enterGoals(@NotNull LPSParser.GoalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#goals}.
	 * @param ctx the parse tree
	 */
	void exitGoals(@NotNull LPSParser.GoalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#reactiveRules}.
	 * @param ctx the parse tree
	 */
	void enterReactiveRules(@NotNull LPSParser.ReactiveRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#reactiveRules}.
	 * @param ctx the parse tree
	 */
	void exitReactiveRules(@NotNull LPSParser.ReactiveRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(@NotNull LPSParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(@NotNull LPSParser.RContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull LPSParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull LPSParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull LPSParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull LPSParser.FormulaContext ctx);
}