// Generated from LPS.g4 by ANTLR 4.x

package com.LPSWorkflow.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LPSParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, END=18, ID=19, NEG=20, WS=21, COMMENT=22;
	public static final String[] tokenNames = {
		"<INVALID>", "'initiates'", "'DomainTheory'", "'->'", "'terminates'", 
		"'&'", "')'", "','", "'ReactiveRules'", "'('", "':'", "'false'", "'Fluents'", 
		"'||'", "'{'", "'Goals'", "'}'", "'<-'", "'.'", "ID", "NEG", "WS", "COMMENT"
	};
	public static final Map<String, Integer> tokenNameToType = Utils.toMap(tokenNames);
	public static final int
		RULE_program = 0, RULE_reactiveRules = 1, RULE_r = 2, RULE_goals = 3, 
		RULE_g = 4, RULE_fluents = 5, RULE_f = 6, RULE_domainTheory = 7, RULE_d = 8, 
		RULE_condition = 9, RULE_conjunction = 10, RULE_precond = 11, RULE_postcond = 12, 
		RULE_initiates = 13, RULE_terminates = 14, RULE_formula = 15, RULE_atom = 16;
	public static final String[] ruleNames = {
		"program", "reactiveRules", "r", "goals", "g", "fluents", "f", "domainTheory", 
		"d", "condition", "conjunction", "precond", "postcond", "initiates", "terminates", 
		"formula", "atom"
	};
	public static final Map<String, Integer> ruleNameToIndex = Utils.toMap(ruleNames);

	@Override
	public String getGrammarFileName() { return "LPS.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }
	@Override
	public ATN getATNWithBypassAlts() { return _ATNWithBypassAlts; }
	@Override
	public void setATNWithBypassAlts(ATN atn) { _ATNWithBypassAlts = atn; }

	@Override
	public Map<String, Integer> getTokenTypeMap() { return tokenNameToType; }
	@Override
	public Map<String, Integer> getRuleIndexMap() { return ruleNameToIndex; }

	public LPSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<DomainTheoryContext> domainTheory() {
			return getRuleContexts(DomainTheoryContext.class);
		}
		public List<GoalsContext> goals() {
			return getRuleContexts(GoalsContext.class);
		}
		public ReactiveRulesContext reactiveRules(int i) {
			return getRuleContext(ReactiveRulesContext.class,i);
		}
		public List<ReactiveRulesContext> reactiveRules() {
			return getRuleContexts(ReactiveRulesContext.class);
		}
		public DomainTheoryContext domainTheory(int i) {
			return getRuleContext(DomainTheoryContext.class,i);
		}
		public List<FluentsContext> fluents() {
			return getRuleContexts(FluentsContext.class);
		}
		public GoalsContext goals(int i) {
			return getRuleContext(GoalsContext.class,i);
		}
		public FluentsContext fluents(int i) {
			return getRuleContext(FluentsContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << 8) | (1L << 12) | (1L << 15))) != 0)) {
				{
				setState(38);
				switch (_input.LA(1)) {
				case 8:
					{
					setState(34); reactiveRules();
					}
					break;
				case 15:
					{
					setState(35); goals();
					}
					break;
				case 12:
					{
					setState(36); fluents();
					}
					break;
				case 2:
					{
					setState(37); domainTheory();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReactiveRulesContext extends ParserRuleContext {
		public List<RContext> r() {
			return getRuleContexts(RContext.class);
		}
		public RContext r(int i) {
			return getRuleContext(RContext.class,i);
		}
		public ReactiveRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reactiveRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterReactiveRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitReactiveRules(this);
		}
	}

	public final ReactiveRulesContext reactiveRules() throws RecognitionException {
		ReactiveRulesContext _localctx = new ReactiveRulesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_reactiveRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); match(8);
			setState(44); match(14);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << ID) | (1L << NEG))) != 0)) {
				{
				{
				setState(45); r();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51); match(16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RContext extends ParserRuleContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public TerminalNode END() { return getToken(LPSParser.END, 0); }
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitR(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_r);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); formula(0);
			setState(54); match(3);
			setState(55); formula(0);
			setState(56); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GoalsContext extends ParserRuleContext {
		public GContext g(int i) {
			return getRuleContext(GContext.class,i);
		}
		public List<GContext> g() {
			return getRuleContexts(GContext.class);
		}
		public GoalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterGoals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitGoals(this);
		}
	}

	public final GoalsContext goals() throws RecognitionException {
		GoalsContext _localctx = new GoalsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_goals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58); match(15);
			setState(59); match(14);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID || _la==NEG) {
				{
				{
				setState(60); g();
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66); match(16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GContext extends ParserRuleContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode END() { return getToken(LPSParser.END, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public GContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_g; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterG(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitG(this);
		}
	}

	public final GContext g() throws RecognitionException {
		GContext _localctx = new GContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_g);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); atom();
			setState(69); match(17);
			setState(70); formula(0);
			setState(71); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FluentsContext extends ParserRuleContext {
		public FContext f(int i) {
			return getRuleContext(FContext.class,i);
		}
		public List<FContext> f() {
			return getRuleContexts(FContext.class);
		}
		public FluentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fluents; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterFluents(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitFluents(this);
		}
	}

	public final FluentsContext fluents() throws RecognitionException {
		FluentsContext _localctx = new FluentsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fluents);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(73); match(12);
			setState(74); match(14);
			setState(83);
			_la = _input.LA(1);
			if (_la==ID || _la==NEG) {
				{
				setState(75); f();
				setState(80);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(76); match(7);
						setState(77); f();
						}
						} 
					}
					setState(82);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				}
			}

			setState(86);
			_la = _input.LA(1);
			if (_la==7) {
				{
				setState(85); match(7);
				}
			}

			setState(88); match(16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public FContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitF(this);
		}
	}

	public final FContext f() throws RecognitionException {
		FContext _localctx = new FContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_f);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DomainTheoryContext extends ParserRuleContext {
		public DContext d(int i) {
			return getRuleContext(DContext.class,i);
		}
		public List<DContext> d() {
			return getRuleContexts(DContext.class);
		}
		public DomainTheoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_domainTheory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterDomainTheory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitDomainTheory(this);
		}
	}

	public final DomainTheoryContext domainTheory() throws RecognitionException {
		DomainTheoryContext _localctx = new DomainTheoryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_domainTheory);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); match(2);
			setState(93); match(14);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 4) | (1L << 11))) != 0)) {
				{
				{
				setState(94); d();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100); match(16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DContext extends ParserRuleContext {
		public PrecondContext precond() {
			return getRuleContext(PrecondContext.class,0);
		}
		public PostcondContext postcond() {
			return getRuleContext(PostcondContext.class,0);
		}
		public DContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitD(this);
		}
	}

	public final DContext d() throws RecognitionException {
		DContext _localctx = new DContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_d);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			switch (_input.LA(1)) {
			case 1:
			case 4:
				{
				setState(102); postcond();
				}
				break;
			case 11:
				{
				setState(103); precond();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConjunctionContext extends ParserRuleContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_conjunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); condition();
			setState(111); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(109); match(5);
				setState(110); condition();
				}
				}
				setState(113); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==5 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrecondContext extends ParserRuleContext {
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public TerminalNode END() { return getToken(LPSParser.END, 0); }
		public PrecondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterPrecond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitPrecond(this);
		}
	}

	public final PrecondContext precond() throws RecognitionException {
		PrecondContext _localctx = new PrecondContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_precond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(11);
			setState(116); match(17);
			setState(117); conjunction();
			setState(118); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostcondContext extends ParserRuleContext {
		public TerminatesContext terminates() {
			return getRuleContext(TerminatesContext.class,0);
		}
		public InitiatesContext initiates() {
			return getRuleContext(InitiatesContext.class,0);
		}
		public TerminalNode END() { return getToken(LPSParser.END, 0); }
		public PostcondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postcond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterPostcond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitPostcond(this);
		}
	}

	public final PostcondContext postcond() throws RecognitionException {
		PostcondContext _localctx = new PostcondContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_postcond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			switch (_input.LA(1)) {
			case 1:
				{
				setState(120); initiates();
				}
				break;
			case 4:
				{
				setState(121); terminates();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(124); match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitiatesContext extends ParserRuleContext {
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public InitiatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initiates; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterInitiates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitInitiates(this);
		}
	}

	public final InitiatesContext initiates() throws RecognitionException {
		InitiatesContext _localctx = new InitiatesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_initiates);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126); match(1);
			setState(127); match(9);
			setState(128); atom();
			setState(129); match(6);
			setState(130); match(17);
			setState(131); conjunction();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TerminatesContext extends ParserRuleContext {
		public ConjunctionContext conjunction() {
			return getRuleContext(ConjunctionContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminates; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterTerminates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitTerminates(this);
		}
	}

	public final TerminatesContext terminates() throws RecognitionException {
		TerminatesContext _localctx = new TerminatesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_terminates);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133); match(4);
			setState(134); match(9);
			setState(135); atom();
			setState(136); match(6);
			setState(137); match(17);
			setState(138); conjunction();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
	 
		public FormulaContext() { }
		public void copyFrom(FormulaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AtomicContext extends FormulaContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomicContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterAtomic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitAtomic(this);
		}
	}
	public static class PartialOrderContext extends FormulaContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public PartialOrderContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterPartialOrder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitPartialOrder(this);
		}
	}
	public static class BracketContext extends FormulaContext {
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public BracketContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterBracket(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitBracket(this);
		}
	}
	public static class SequenceContext extends FormulaContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public SequenceContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitSequence(this);
		}
	}
	public static class ConcurrentContext extends FormulaContext {
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public ConcurrentContext(FormulaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterConcurrent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitConcurrent(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		return formula(0);
	}

	private FormulaContext formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FormulaContext _localctx = new FormulaContext(_ctx, _parentState);
		FormulaContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_formula, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			switch (_input.LA(1)) {
			case 9:
				{
				_localctx = new BracketContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(141); match(9);
				setState(142); formula(0);
				setState(143); match(6);
				}
				break;
			case ID:
			case NEG:
				{
				_localctx = new AtomicContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145); atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(159);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(157);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new SequenceContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(148);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(149); match(7);
						setState(150); formula(6);
						}
						break;
					case 2:
						{
						_localctx = new ConcurrentContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(151);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(152); match(10);
						setState(153); formula(5);
						}
						break;
					case 3:
						{
						_localctx = new PartialOrderContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(154);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(155); match(13);
						setState(156); formula(4);
						}
						break;
					}
					} 
				}
				setState(161);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LPSParser.ID, 0); }
		public TerminalNode NEG() { return getToken(LPSParser.NEG, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LPSListener ) ((LPSListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NEG) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15: return formula_sempred((FormulaContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean formula_sempred(FormulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 5);
		case 1: return precpred(_ctx, 4);
		case 2: return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0f63\ub3d0\u10be\u9b29\u438c\u6c08\uc57f\u1da2\3\30\u00a7\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\7\2)\n\2\f\2\16\2,\13\2\3\3\3\3\3\3\7\3\61\n\3\f\3\16"+
		"\3\64\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5@\n\5\f\5\16\5C"+
		"\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\7\7Q\n\7\f\7\16"+
		"\7T\13\7\5\7V\n\7\3\7\5\7Y\n\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\7\tb\n\t\f"+
		"\t\16\te\13\t\3\t\3\t\3\n\3\n\5\nk\n\n\3\13\3\13\3\f\3\f\3\f\6\fr\n\f"+
		"\r\f\16\fs\3\r\3\r\3\r\3\r\3\r\3\16\3\16\5\16}\n\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\5\21\u0095\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\7\21\u00a0\n\21\f\21\16\21\u00a3\13\21\3\22\3\22\3\22\2\3 "+
		"\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\3\3\2\25\26\u00a6\2*\3"+
		"\2\2\2\4-\3\2\2\2\6\67\3\2\2\2\b<\3\2\2\2\nF\3\2\2\2\fK\3\2\2\2\16\\\3"+
		"\2\2\2\20^\3\2\2\2\22j\3\2\2\2\24l\3\2\2\2\26n\3\2\2\2\30u\3\2\2\2\32"+
		"|\3\2\2\2\34\u0080\3\2\2\2\36\u0087\3\2\2\2 \u0094\3\2\2\2\"\u00a4\3\2"+
		"\2\2$)\5\4\3\2%)\5\b\5\2&)\5\f\7\2\')\5\20\t\2($\3\2\2\2(%\3\2\2\2(&\3"+
		"\2\2\2(\'\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\3\3\2\2\2,*\3\2\2\2-"+
		".\7\n\2\2.\62\7\20\2\2/\61\5\6\4\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2"+
		"\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62\3\2\2\2\65\66\7\22\2\2\66\5\3\2"+
		"\2\2\678\5 \21\289\7\5\2\29:\5 \21\2:;\7\24\2\2;\7\3\2\2\2<=\7\21\2\2"+
		"=A\7\20\2\2>@\5\n\6\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2"+
		"\2CA\3\2\2\2DE\7\22\2\2E\t\3\2\2\2FG\5\"\22\2GH\7\23\2\2HI\5 \21\2IJ\7"+
		"\24\2\2J\13\3\2\2\2KL\7\16\2\2LU\7\20\2\2MR\5\16\b\2NO\7\t\2\2OQ\5\16"+
		"\b\2PN\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SV\3\2\2\2TR\3\2\2\2UM\3\2"+
		"\2\2UV\3\2\2\2VX\3\2\2\2WY\7\t\2\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\7\22"+
		"\2\2[\r\3\2\2\2\\]\5\"\22\2]\17\3\2\2\2^_\7\4\2\2_c\7\20\2\2`b\5\22\n"+
		"\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7\22"+
		"\2\2g\21\3\2\2\2hk\5\32\16\2ik\5\30\r\2jh\3\2\2\2ji\3\2\2\2k\23\3\2\2"+
		"\2lm\5\"\22\2m\25\3\2\2\2nq\5\24\13\2op\7\7\2\2pr\5\24\13\2qo\3\2\2\2"+
		"rs\3\2\2\2sq\3\2\2\2st\3\2\2\2t\27\3\2\2\2uv\7\r\2\2vw\7\23\2\2wx\5\26"+
		"\f\2xy\7\24\2\2y\31\3\2\2\2z}\5\34\17\2{}\5\36\20\2|z\3\2\2\2|{\3\2\2"+
		"\2}~\3\2\2\2~\177\7\24\2\2\177\33\3\2\2\2\u0080\u0081\7\3\2\2\u0081\u0082"+
		"\7\13\2\2\u0082\u0083\5\"\22\2\u0083\u0084\7\b\2\2\u0084\u0085\7\23\2"+
		"\2\u0085\u0086\5\26\f\2\u0086\35\3\2\2\2\u0087\u0088\7\6\2\2\u0088\u0089"+
		"\7\13\2\2\u0089\u008a\5\"\22\2\u008a\u008b\7\b\2\2\u008b\u008c\7\23\2"+
		"\2\u008c\u008d\5\26\f\2\u008d\37\3\2\2\2\u008e\u008f\b\21\1\2\u008f\u0090"+
		"\7\13\2\2\u0090\u0091\5 \21\2\u0091\u0092\7\b\2\2\u0092\u0095\3\2\2\2"+
		"\u0093\u0095\5\"\22\2\u0094\u008e\3\2\2\2\u0094\u0093\3\2\2\2\u0095\u00a1"+
		"\3\2\2\2\u0096\u0097\f\7\2\2\u0097\u0098\7\t\2\2\u0098\u00a0\5 \21\b\u0099"+
		"\u009a\f\6\2\2\u009a\u009b\7\f\2\2\u009b\u00a0\5 \21\7\u009c\u009d\f\5"+
		"\2\2\u009d\u009e\7\17\2\2\u009e\u00a0\5 \21\6\u009f\u0096\3\2\2\2\u009f"+
		"\u0099\3\2\2\2\u009f\u009c\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a2!\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5"+
		"\t\2\2\2\u00a5#\3\2\2\2\20(*\62ARUXcjs|\u0094\u009f\u00a1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	public static ATN _ATNWithBypassAlts;
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}