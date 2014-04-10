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
	 * Enter a parse tree produced by {@link LPSParser#f}.
	 * @param ctx the parse tree
	 */
	void enterF(@NotNull LPSParser.FContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#f}.
	 * @param ctx the parse tree
	 */
	void exitF(@NotNull LPSParser.FContext ctx);
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
	 * Enter a parse tree produced by {@link LPSParser#d}.
	 * @param ctx the parse tree
	 */
	void enterD(@NotNull LPSParser.DContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#d}.
	 * @param ctx the parse tree
	 */
	void exitD(@NotNull LPSParser.DContext ctx);
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
	 * Enter a parse tree produced by {@link LPSParser#Atomic}.
	 * @param ctx the parse tree
	 */
	void enterAtomic(@NotNull LPSParser.AtomicContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#Atomic}.
	 * @param ctx the parse tree
	 */
	void exitAtomic(@NotNull LPSParser.AtomicContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#PartialOrder}.
	 * @param ctx the parse tree
	 */
	void enterPartialOrder(@NotNull LPSParser.PartialOrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#PartialOrder}.
	 * @param ctx the parse tree
	 */
	void exitPartialOrder(@NotNull LPSParser.PartialOrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#terminates}.
	 * @param ctx the parse tree
	 */
	void enterTerminates(@NotNull LPSParser.TerminatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#terminates}.
	 * @param ctx the parse tree
	 */
	void exitTerminates(@NotNull LPSParser.TerminatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#initiates}.
	 * @param ctx the parse tree
	 */
	void enterInitiates(@NotNull LPSParser.InitiatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#initiates}.
	 * @param ctx the parse tree
	 */
	void exitInitiates(@NotNull LPSParser.InitiatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(@NotNull LPSParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(@NotNull LPSParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#Bracket}.
	 * @param ctx the parse tree
	 */
	void enterBracket(@NotNull LPSParser.BracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#Bracket}.
	 * @param ctx the parse tree
	 */
	void exitBracket(@NotNull LPSParser.BracketContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#fluents}.
	 * @param ctx the parse tree
	 */
	void enterFluents(@NotNull LPSParser.FluentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#fluents}.
	 * @param ctx the parse tree
	 */
	void exitFluents(@NotNull LPSParser.FluentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#Concurrent}.
	 * @param ctx the parse tree
	 */
	void enterConcurrent(@NotNull LPSParser.ConcurrentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#Concurrent}.
	 * @param ctx the parse tree
	 */
	void exitConcurrent(@NotNull LPSParser.ConcurrentContext ctx);
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
	 * Enter a parse tree produced by {@link LPSParser#precond}.
	 * @param ctx the parse tree
	 */
	void enterPrecond(@NotNull LPSParser.PrecondContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#precond}.
	 * @param ctx the parse tree
	 */
	void exitPrecond(@NotNull LPSParser.PrecondContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#postcond}.
	 * @param ctx the parse tree
	 */
	void enterPostcond(@NotNull LPSParser.PostcondContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#postcond}.
	 * @param ctx the parse tree
	 */
	void exitPostcond(@NotNull LPSParser.PostcondContext ctx);
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
	 * Enter a parse tree produced by {@link LPSParser#domainTheory}.
	 * @param ctx the parse tree
	 */
	void enterDomainTheory(@NotNull LPSParser.DomainTheoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#domainTheory}.
	 * @param ctx the parse tree
	 */
	void exitDomainTheory(@NotNull LPSParser.DomainTheoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPSParser#Sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequence(@NotNull LPSParser.SequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPSParser#Sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequence(@NotNull LPSParser.SequenceContext ctx);
}