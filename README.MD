# Reproducer for quarkus native + FIPS + service issue

This is reproducer for a problem in which running quarkus app in native mode on FIPS enabled machine causes it to fail.

By default, this reproducer uses quarkus 3.15.1.
With that you can reproduce the issue using:

```bash
mvn clean verify -Dnative
```

Output should fail with `Caused by: java.lang.NullPointerException: Cannot invoke "java.util.Set.iterator()" because "services" is null` failure.

Full expected stack trace:
```
Fatal error: org.graalvm.compiler.debug.GraalError: java.lang.NullPointerException: Cannot invoke "java.util.Set.iterator()" because "services" is null
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AnalysisFuture.setException(AnalysisFuture.java:49)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:322)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.AnalysisFuture.ensureDone(AnalysisFuture.java:63)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisElement.lambda$execute$2(AnalysisElement.java:220)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.executeCommand(CompletionExecutor.java:187)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.util.CompletionExecutor.lambda$executeService$0(CompletionExecutor.java:171)
	at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1423)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:387)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1312)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1843)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1808)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:188)
Caused by: java.lang.NullPointerException: Cannot invoke "java.util.Set.iterator()" because "services" is null
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.SecurityServicesFeature.doRegisterServices(SecurityServicesFeature.java:798)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.SecurityServicesFeature.lambda$registerServices$17(SecurityServicesFeature.java:789)
	at java.base/java.util.concurrent.ConcurrentHashMap.computeIfAbsent(ConcurrentHashMap.java:1740)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.SecurityServicesFeature.registerServices(SecurityServicesFeature.java:788)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.SecurityServicesFeature.registerServices(SecurityServicesFeature.java:766)
	at org.graalvm.nativeimage.builder/com.oracle.svm.hosted.SecurityServicesFeature.lambda$registerSASLReachabilityHandlers$12(SecurityServicesFeature.java:699)
	at org.graalvm.nativeimage.pointsto/com.oracle.graal.pointsto.meta.AnalysisElement$ElementNotification.lambda$notifyCallback$0(AnalysisElement.java:147)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:317)
	... 10 more
```