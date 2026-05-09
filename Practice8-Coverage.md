# Practice 8 - Coverage

Date: 2026-04-28

## 1. Original JaCoCo report (before)

Baseline report file:
- `target/site/docs-core/jacoco/index.html`
- `target/site/docs-core/jacoco/jacoco.csv`

Baseline metrics (from `target/site/docs-core/jacoco/jacoco.csv`):
- Instruction coverage: 27.04% (5480/20268)
- Branch coverage: 22.15% (256/1156)

Target class before:
- `SecurityUtil`: `Docs Core,com.sismics.docs.core.util,SecurityUtil,48,0,11,0,12,0,9,0,3,0`
- Meaning: instruction covered = 0, branch covered = 0

## 2. Added test case(s)

Added file:
- `docs-core/src/test/java/com/sismics/docs/core/util/TestSecurityUtil.java`
- `docs-core/src/test/java/com/sismics/util/jpa/TestDialectUtil.java`
- `docs-core/src/test/java/com/sismics/util/context/TestThreadLocalContext.java`

Added tests (simple and focused):
- `skipAclCheckAdminTest()`
- `skipAclCheckAdministratorsTest()`
- `skipAclCheckRegularUserTest()`
- `isObjectNotFoundH2Test()`
- `isObjectNotFoundPostgresqlTest()`
- `transformPostgresqlTest()`
- `transformH2Test()`
- `getReturnsSameInstanceUntilCleanupTest()`
- `cleanupCreatesNewInstanceTest()`

These tests stay small and focused. `SecurityUtil` and `ThreadLocalContext` are pure unit tests, while `DialectUtil` uses a tiny reflection helper to switch the driver string and exercise both branches without needing application code changes.

## 3. Execute the new test class

Command:
```bash
mvn -pl docs-core -Dtest=TestSecurityUtil,TestDialectUtil,TestThreadLocalContext test
```

Result:
- Tests run: 9
- Failures: 0
- Errors: 0
- Skipped: 0
- BUILD SUCCESS

## 4. Regenerate JaCoCo report (after)

Command:
```bash
mvn -pl docs-core jacoco:report
```

New report file:
- `docs-core/target/site/jacoco/index.html`
- `docs-core/target/site/jacoco/jacoco.csv`

After metrics (from `docs-core/target/site/jacoco/jacoco.csv`):
- Instruction coverage: 27.38% (5549/20268)
- Branch coverage: 23.27% (269/1156)

Target class after:
- `SecurityUtil`: `Docs Core,com.sismics.docs.core.util,SecurityUtil,36,12,7,4,11,1,6,3,2,1`
- Meaning: instruction covered = 12, branch covered = 4
- `DialectUtil`: `Docs Core,com.sismics.util.jpa,DialectUtil,3,89,0,18,1,18,1,12,1,3`
- `ThreadLocalContext`: `Docs Core,com.sismics.util.context,ThreadLocalContext,16,58,2,6,6,20,3,9,1,7`

## 5. Coverage improvement summary

- Instruction coverage increased: 27.04% -> 27.38%
- Branch coverage increased: 22.15% -> 23.27%
- Both required metrics improved after adding the new test classes.
