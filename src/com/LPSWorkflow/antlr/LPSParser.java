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
		RULE_condition = 9, RULE_precond = 10, RULE_postcond = 11, RULE_initiates = 12, 
		RULE_terminates = 13, RULE_formula = 14, RULE_atom = 15;
	public static final String[] ruleNames = {
		"program", "reactiveRules", "r", "goals", "g", "fluents", "f", "domainTheory", 
		"d", "condition", "precond", "postcond", "initiates", "terminates", "formula", 
		"atom"
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
		public List<GoalsContext> goals() {
			return getRuleContexts(GoalsContext.class);
		}
		public ReactiveRulesContext reactiveRules(int i) {
			return getRuleContext(ReactiveRulesContext.class,i);
		}
		public List<ReactiveRulesContext> reactiveRules() {
			return getRuleContexts(ReactiveRulesContext.class);
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
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 8) | (1L << 12) | (1L << 15))) != 0)) {
				{
				setState(35);
				switch (_input.LA(1)) {
				case 8:
					{
					setState(32); reactiveRules();
					}
					break;
				case 15:
					{
					setState(33); goals();
					}
					break;
				case 12:
					{
					setState(34); fluents();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(39);
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
			setState(40); match(8);
			setState(41); match(14);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 9) | (1L << ID) | (1L << NEG))) != 0)) {
				{
				{
				setState(42); r();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48); match(16);
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
			setState(50); formula(0);
			setState(51); match(3);
			setState(52); formula(0);
			setState(53); match(END);
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
			setState(55); match(15);
			setState(56); match(14);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID || _la==NEG) {
				{
				{
				setState(57); g();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63); match(16);
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
			setState(65); atom();
			setState(66); match(17);
			setState(67); formula(0);
			setState(68); match(END);
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
			setState(70); match(12);
			setState(71); match(14);
			setState(80);
			_la = _input.LA(1);
			if (_la==ID || _la==NEG) {
				{
				setState(72); f();
				setState(77);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(73); match(7);
						setState(74); f();
						}
						} 
					}
					setState(79);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
				}
				}
			}

			setState(83);
			_la = _input.LA(1);
			if (_la==7) {
				{
				setState(82); match(7);
				}
			}

			setState(85); match(16);
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
			setState(87); atom();
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
			setState(89); match(2);
			setState(90); match(14);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 4) | (1L << 11))) != 0)) {
				{
				{
				setState(91); d();
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97); match(16);
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
			setState(101);
			switch (_input.LA(1)) {
			case 1:
			case 4:
				{
				setState(99); postcond();
				}
				break;
			case 11:
				{
				setState(100); precond();
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
			setState(103); atom();
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
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
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
		enterRule(_localctx, 20, RULE_precond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(11);
			setState(106); match(17);
			setState(107); condition();
			setState(110); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(108); match(5);
				setState(109); condition();
				}
				}
				setState(112); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==5 );
			setState(114); match(END);
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
		enterRule(_localctx, 22, RULE_postcond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			switch (_input.LA(1)) {
			case 1:
				{
				setState(116); initiates();
				}
				break;
			case 4:
				{
				setState(117); terminates();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(120); match(END);
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
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
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
		enterRule(_localctx, 24, RULE_initiates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); match(1);
			setState(123); match(9);
			setState(124); atom();
			setState(125); match(6);
			setState(126); match(17);
			setState(127); condition();
			setState(130); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128); match(5);
				setState(129); condition();
				}
				}
				setState(132); 
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

	public static class TerminatesContext extends ParserRuleContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
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
		enterRule(_localctx, 26, RULE_terminates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); match(4);
			setState(135); match(9);
			setState(136); atom();
			setState(137); match(6);
			setState(138); match(17);
			setState(139); condition();
			setState(142); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140); match(5);
				setState(141); condition();
				}
				}
				setState(144); 
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_formula, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			switch (_input.LA(1)) {
			case 9:
				{
				_localctx = new BracketContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(147); match(9);
				setState(148); formula(0);
				setState(149); match(6);
				}
				break;
			case ID:
			case NEG:
				{
				_localctx = new AtomicContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151); atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(165);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(163);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new SequenceContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(154);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(155); match(7);
						setState(156); formula(6);
						}
						break;
					case 2:
						{
						_localctx = new ConcurrentContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(157);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(158); match(10);
						setState(159); formula(5);
						}
						break;
					case 3:
						{
						_localctx = new PartialOrderContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(160);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(161); match(13);
						setState(162); formula(4);
						}
						break;
					}
					} 
				}
				setState(167);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		enterRule(_localctx, 30, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
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
		case 14: return formula_sempred((FormulaContext)_localctx, predIndex);
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
		"\3\u0f63\ub3d0\u10be\u9b29\u438c\u6c08\uc57f\u1da2\3\30\u00ad\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2"+
		"\3\2\7\2&\n\2\f\2\16\2)\13\2\3\3\3\3\3\3\7\3.\n\3\f\3\16\3\61\13\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5=\n\5\f\5\16\5@\13\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\7\7N\n\7\f\7\16\7Q\13\7\5\7S\n"+
		"\7\3\7\5\7V\n\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\7\t_\n\t\f\t\16\tb\13\t\3"+
		"\t\3\t\3\n\3\n\5\nh\n\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\6\fq\n\f\r\f\16"+
		"\fr\3\f\3\f\3\r\3\r\5\ry\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\6\16\u0085\n\16\r\16\16\16\u0086\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\6\17\u0091\n\17\r\17\16\17\u0092\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\5\20\u009b\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00a6"+
		"\n\20\f\20\16\20\u00a9\13\20\3\21\3\21\3\21\2\3\36\22\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \2\3\3\2\25\26\u00ae\2\'\3\2\2\2\4*\3\2\2\2\6\64"+
		"\3\2\2\2\b9\3\2\2\2\nC\3\2\2\2\fH\3\2\2\2\16Y\3\2\2\2\20[\3\2\2\2\22g"+
		"\3\2\2\2\24i\3\2\2\2\26k\3\2\2\2\30x\3\2\2\2\32|\3\2\2\2\34\u0088\3\2"+
		"\2\2\36\u009a\3\2\2\2 \u00aa\3\2\2\2\"&\5\4\3\2#&\5\b\5\2$&\5\f\7\2%\""+
		"\3\2\2\2%#\3\2\2\2%$\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\3\3\2\2"+
		"\2)\'\3\2\2\2*+\7\n\2\2+/\7\20\2\2,.\5\6\4\2-,\3\2\2\2.\61\3\2\2\2/-\3"+
		"\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2\62\63\7\22\2\2\63\5\3\2\2"+
		"\2\64\65\5\36\20\2\65\66\7\5\2\2\66\67\5\36\20\2\678\7\24\2\28\7\3\2\2"+
		"\29:\7\21\2\2:>\7\20\2\2;=\5\n\6\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2"+
		"\2\2?A\3\2\2\2@>\3\2\2\2AB\7\22\2\2B\t\3\2\2\2CD\5 \21\2DE\7\23\2\2EF"+
		"\5\36\20\2FG\7\24\2\2G\13\3\2\2\2HI\7\16\2\2IR\7\20\2\2JO\5\16\b\2KL\7"+
		"\t\2\2LN\5\16\b\2MK\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PS\3\2\2\2QO"+
		"\3\2\2\2RJ\3\2\2\2RS\3\2\2\2SU\3\2\2\2TV\7\t\2\2UT\3\2\2\2UV\3\2\2\2V"+
		"W\3\2\2\2WX\7\22\2\2X\r\3\2\2\2YZ\5 \21\2Z\17\3\2\2\2[\\\7\4\2\2\\`\7"+
		"\20\2\2]_\5\22\n\2^]\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2ac\3\2\2\2b"+
		"`\3\2\2\2cd\7\22\2\2d\21\3\2\2\2eh\5\30\r\2fh\5\26\f\2ge\3\2\2\2gf\3\2"+
		"\2\2h\23\3\2\2\2ij\5 \21\2j\25\3\2\2\2kl\7\r\2\2lm\7\23\2\2mp\5\24\13"+
		"\2no\7\7\2\2oq\5\24\13\2pn\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2st\3\2"+
		"\2\2tu\7\24\2\2u\27\3\2\2\2vy\5\32\16\2wy\5\34\17\2xv\3\2\2\2xw\3\2\2"+
		"\2yz\3\2\2\2z{\7\24\2\2{\31\3\2\2\2|}\7\3\2\2}~\7\13\2\2~\177\5 \21\2"+
		"\177\u0080\7\b\2\2\u0080\u0081\7\23\2\2\u0081\u0084\5\24\13\2\u0082\u0083"+
		"\7\7\2\2\u0083\u0085\5\24\13\2\u0084\u0082\3\2\2\2\u0085\u0086\3\2\2\2"+
		"\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\33\3\2\2\2\u0088\u0089"+
		"\7\6\2\2\u0089\u008a\7\13\2\2\u008a\u008b\5 \21\2\u008b\u008c\7\b\2\2"+
		"\u008c\u008d\7\23\2\2\u008d\u0090\5\24\13\2\u008e\u008f\7\7\2\2\u008f"+
		"\u0091\5\24\13\2\u0090\u008e\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3"+
		"\2\2\2\u0092\u0093\3\2\2\2\u0093\35\3\2\2\2\u0094\u0095\b\20\1\2\u0095"+
		"\u0096\7\13\2\2\u0096\u0097\5\36\20\2\u0097\u0098\7\b\2\2\u0098\u009b"+
		"\3\2\2\2\u0099\u009b\5 \21\2\u009a\u0094\3\2\2\2\u009a\u0099\3\2\2\2\u009b"+
		"\u00a7\3\2\2\2\u009c\u009d\f\7\2\2\u009d\u009e\7\t\2\2\u009e\u00a6\5\36"+
		"\20\b\u009f\u00a0\f\6\2\2\u00a0\u00a1\7\f\2\2\u00a1\u00a6\5\36\20\7\u00a2"+
		"\u00a3\f\5\2\2\u00a3\u00a4\7\17\2\2\u00a4\u00a6\5\36\20\6\u00a5\u009c"+
		"\3\2\2\2\u00a5\u009f\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\37\3\2\2\2\u00a9\u00a7\3\2\2"+
		"\2\u00aa\u00ab\t\2\2\2\u00ab!\3\2\2\2\22%\'/>ORU`grx\u0086\u0092\u009a"+
		"\u00a5\u00a7";
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