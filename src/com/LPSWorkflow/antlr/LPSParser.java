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
		T__11=1, T__10=2, T__9=3, T__8=4, T__7=5, T__6=6, T__5=7, T__4=8, T__3=9, 
		T__2=10, T__1=11, T__0=12, END=13, ID=14, NEG=15, WS=16;
	public static final String[] tokenNames = {
		"<INVALID>", "'->'", "'||'", "'{'", "')'", "'ReactiveRules'", "','", "':'", 
		"'('", "'Goals'", "'}'", "'Fluents'", "'<-'", "'.'", "ID", "NEG", "WS"
	};
	public static final Map<String, Integer> tokenNameToType = Utils.toMap(tokenNames);
	public static final int
		RULE_program = 0, RULE_reactiveRules = 1, RULE_r = 2, RULE_goals = 3, 
		RULE_g = 4, RULE_fluents = 5, RULE_f = 6, RULE_formula = 7, RULE_atom = 8;
	public static final String[] ruleNames = {
		"program", "reactiveRules", "r", "goals", "g", "fluents", "f", "formula", 
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
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 9) | (1L << 11))) != 0)) {
				{
				setState(21);
				switch (_input.LA(1)) {
				case 5:
					{
					setState(18); reactiveRules();
					}
					break;
				case 9:
					{
					setState(19); goals();
					}
					break;
				case 11:
					{
					setState(20); fluents();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(25);
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
		public TerminalNode END(int i) {
			return getToken(LPSParser.END, i);
		}
		public List<RContext> r() {
			return getRuleContexts(RContext.class);
		}
		public RContext r(int i) {
			return getRuleContext(RContext.class,i);
		}
		public List<TerminalNode> END() { return getTokens(LPSParser.END); }
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(26); match(5);
			setState(27); match(3);
			setState(36);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 8) | (1L << ID) | (1L << NEG))) != 0)) {
				{
				setState(28); r();
				setState(33);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(29); match(END);
						setState(30); r();
						}
						} 
					}
					setState(35);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				}
			}

			setState(39);
			_la = _input.LA(1);
			if (_la==END) {
				{
				setState(38); match(END);
				}
			}

			setState(41); match(10);
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
			setState(43); formula(0);
			setState(44); match(1);
			setState(45); formula(0);
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
		public TerminalNode END(int i) {
			return getToken(LPSParser.END, i);
		}
		public GContext g(int i) {
			return getRuleContext(GContext.class,i);
		}
		public List<GContext> g() {
			return getRuleContexts(GContext.class);
		}
		public List<TerminalNode> END() { return getTokens(LPSParser.END); }
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(47); match(9);
			setState(48); match(3);
			setState(57);
			_la = _input.LA(1);
			if (_la==ID || _la==NEG) {
				{
				setState(49); g();
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(50); match(END);
						setState(51); g();
						}
						} 
					}
					setState(56);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				}
			}

			setState(60);
			_la = _input.LA(1);
			if (_la==END) {
				{
				setState(59); match(END);
				}
			}

			setState(62); match(10);
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
			setState(64); atom();
			setState(65); match(12);
			setState(66); formula(0);
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
			setState(68); match(11);
			setState(69); match(3);
			setState(78);
			_la = _input.LA(1);
			if (_la==ID || _la==NEG) {
				{
				setState(70); f();
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(71); match(6);
						setState(72); f();
						}
						} 
					}
					setState(77);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				}
			}

			setState(81);
			_la = _input.LA(1);
			if (_la==6) {
				{
				setState(80); match(6);
				}
			}

			setState(83); match(10);
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
			setState(85); atom();
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
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_formula, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			switch (_input.LA(1)) {
			case 8:
				{
				_localctx = new BracketContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(88); match(8);
				setState(89); formula(0);
				setState(90); match(4);
				}
				break;
			case ID:
			case NEG:
				{
				_localctx = new AtomicContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92); atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(106);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(104);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new SequenceContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(95);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(96); match(6);
						setState(97); formula(6);
						}
						break;
					case 2:
						{
						_localctx = new ConcurrentContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(98);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(99); match(7);
						setState(100); formula(5);
						}
						break;
					case 3:
						{
						_localctx = new PartialOrderContext(new FormulaContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_formula);
						setState(101);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(102); match(2);
						setState(103); formula(4);
						}
						break;
					}
					} 
				}
				setState(108);
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
		enterRule(_localctx, 16, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
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
		case 7: return formula_sempred((FormulaContext)_localctx, predIndex);
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
		"\3\u0f63\ub3d0\u10be\u9b29\u438c\u6c08\uc57f\u1da2\3\22r\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\7\2\30\n\2\f\2\16\2\33\13\2\3\3\3\3\3\3\3\3\3\3\7\3\"\n\3\f\3\16\3%\13"+
		"\3\5\3\'\n\3\3\3\5\3*\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\7\5\67\n\5\f\5\16\5:\13\5\5\5<\n\5\3\5\5\5?\n\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\7\7L\n\7\f\7\16\7O\13\7\5\7Q\n\7\3\7\5\7T\n\7\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t`\n\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\7\tk\n\t\f\t\16\tn\13\t\3\n\3\n\3\n\2\3\20\13\2\4\6\b\n"+
		"\f\16\20\22\2\3\3\2\20\21x\2\31\3\2\2\2\4\34\3\2\2\2\6-\3\2\2\2\b\61\3"+
		"\2\2\2\nB\3\2\2\2\fF\3\2\2\2\16W\3\2\2\2\20_\3\2\2\2\22o\3\2\2\2\24\30"+
		"\5\4\3\2\25\30\5\b\5\2\26\30\5\f\7\2\27\24\3\2\2\2\27\25\3\2\2\2\27\26"+
		"\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\31"+
		"\3\2\2\2\34\35\7\7\2\2\35&\7\5\2\2\36#\5\6\4\2\37 \7\17\2\2 \"\5\6\4\2"+
		"!\37\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\'\3\2\2\2%#\3\2\2\2&\36\3"+
		"\2\2\2&\'\3\2\2\2\')\3\2\2\2(*\7\17\2\2)(\3\2\2\2)*\3\2\2\2*+\3\2\2\2"+
		"+,\7\f\2\2,\5\3\2\2\2-.\5\20\t\2./\7\3\2\2/\60\5\20\t\2\60\7\3\2\2\2\61"+
		"\62\7\13\2\2\62;\7\5\2\2\638\5\n\6\2\64\65\7\17\2\2\65\67\5\n\6\2\66\64"+
		"\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29<\3\2\2\2:8\3\2\2\2;\63\3\2"+
		"\2\2;<\3\2\2\2<>\3\2\2\2=?\7\17\2\2>=\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\7"+
		"\f\2\2A\t\3\2\2\2BC\5\22\n\2CD\7\16\2\2DE\5\20\t\2E\13\3\2\2\2FG\7\r\2"+
		"\2GP\7\5\2\2HM\5\16\b\2IJ\7\b\2\2JL\5\16\b\2KI\3\2\2\2LO\3\2\2\2MK\3\2"+
		"\2\2MN\3\2\2\2NQ\3\2\2\2OM\3\2\2\2PH\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RT\7\b"+
		"\2\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\f\2\2V\r\3\2\2\2WX\5\22\n\2X\17"+
		"\3\2\2\2YZ\b\t\1\2Z[\7\n\2\2[\\\5\20\t\2\\]\7\6\2\2]`\3\2\2\2^`\5\22\n"+
		"\2_Y\3\2\2\2_^\3\2\2\2`l\3\2\2\2ab\f\7\2\2bc\7\b\2\2ck\5\20\t\bde\f\6"+
		"\2\2ef\7\t\2\2fk\5\20\t\7gh\f\5\2\2hi\7\4\2\2ik\5\20\t\6ja\3\2\2\2jd\3"+
		"\2\2\2jg\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2m\21\3\2\2\2nl\3\2\2\2o"+
		"p\t\2\2\2p\23\3\2\2\2\20\27\31#&)8;>MPS_jl";
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