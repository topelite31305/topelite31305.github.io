import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Generates a static educational website on Safety, Law, and Ethics in Computing
 * (Performance Standard 5.3; 5.3.4 and 5.3.5).
 *
 * Run with Java 11+ (no compilation needed):
 *     java GenerateSite.java
 *
 * It writes index.html, style.css, and app.js into the current folder
 * (or into a folder you pass as an argument), ready for GitHub Pages.
 */
public class GenerateSite {

    public static void main(String[] args) throws IOException {
        Path out = Paths.get(args.length > 0 ? args[0] : ".");
        Files.createDirectories(out);

        write(out.resolve("index.html"), INDEX_HTML);
        write(out.resolve("style.css"), STYLE_CSS);
        write(out.resolve("app.js"), APP_JS);

        System.out.println("Website generated in: " + out.toAbsolutePath());
        System.out.println("Next: commit these files and enable GitHub Pages (see instructions).");
    }

    private static void write(Path file, String content) throws IOException {
        Files.writeString(file, content);
        System.out.println("  wrote " + file.getFileName());
    }

    // ------------------------------------------------------------------
    // index.html
    // ------------------------------------------------------------------
    private static final String INDEX_HTML = """
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Safety, Law, and Ethics in Computing</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>

<header>
  <h1>Safety, Law, and Ethics in Computing</h1>
  <p class="subtitle">Can your phone's map app be used against you? Find out.</p>
  <nav>
    <a href="#start">Start</a>
    <a href="#ethics">Ethics</a>
    <a href="#law">Law</a>
    <a href="#quiz">Quiz</a>
    <a href="#lab">Privacy Lab</a>
    <a href="#sources">Sources</a>
    <a href="#peer">Peer Test</a>
  </nav>
</header>

<main>

<section id="start">
  <h2>How to use this site</h2>
  <p>This site covers two standards:</p>
  <ul>
    <li><strong>5.3.4</strong> Discuss the role of ethics in technologies.</li>
    <li><strong>5.3.5</strong> Discuss laws and regulations that impact the development and use of software.</li>
  </ul>
  <p>Work through the sections in order. Each one ends with an activity. Read the feedback carefully, even when you answer correctly, because the explanations contain the reasoning you'll need on tests.</p>
  <div class="card scenario">
    <h3>Scenario</h3>
    <p>You install a free navigation app. It asks for location access "always," and its privacy policy says it may share "usage data" with partners. Before reading on, write down one thing you think the app could learn about you over a month.</p>
  </div>
</section>

<section id="ethics">
  <h2>Part 1: Ethics in Technology (5.3.4)</h2>
  <p>Ethics asks what <em>should</em> be done, even when nothing forces you to do it. In computing, ethical questions often arise where the law is silent, unclear, or has not caught up with new technology.</p>

  <div class="card">
    <h3>Key idea: location data is revealing</h3>
    <p>A single location point looks harmless. A month of location points can reveal where you sleep, where you work, where you worship, which clinic you visit, and who you spend time with. The ethical issue is not whether the app <em>can</em> collect this, but whether the people it collects from would reasonably expect and agree to it.</p>
  </div>

  <div class="card">
    <h3>Think about it</h3>
    <ol>
      <li>If a company promised to delete your location history after 30 days, would that fully solve the ethical concern? Why or why not?</li>
      <li>Is it ethical to collect data that users agreed to in a long policy few people read? What would make consent meaningful?</li>
      <li>Who benefits from location data, and who bears the risk?</li>
    </ol>
  </div>
</section>

<section id="law">
  <h2>Part 2: Laws and Regulations (5.3.5)</h2>
  <p>A law is a rule that is enforceable by a government. Ethics is a standard of right and wrong that may or may not be written into law. A practice can be legal but unethical, and an ethical concern may become illegal once a legislature acts on it.</p>

  <div class="card">
    <h3>Ethics or law?</h3>
    <p>For each example, decide whether the main issue is primarily <strong>ethical</strong>, <strong>legal</strong>, or <strong>both</strong>. Then read the explanation.</p>
    <div id="sorter"></div>
  </div>

  <div class="card">
    <h3>Three laws that touch location and personal data</h3>
    <table>
      <tr><th>Law</th><th>Jurisdiction</th><th>What it does (summary)</th></tr>
      <tr>
        <td>General Data Protection Regulation (GDPR), Regulation (EU) 2016/679</td>
        <td>European Union</td>
        <td>Treats location data as personal data. Requires a lawful basis (such as consent) before processing it, and gives people rights to access and delete their data.</td>
      </tr>
      <tr>
        <td>California Consumer Privacy Act, as amended by the CPRA (California Civil Code §1798.100 et seq.)</td>
        <td>California, USA</td>
        <td>Gives California residents the right to know what personal information is collected and to opt out of its sale or sharing. Precise geolocation is treated as "sensitive personal information."</td>
      </tr>
      <tr>
        <td>Federal Trade Commission Act, Section 5</td>
        <td>United States (federal)</td>
        <td>Prohibits unfair or deceptive practices. The FTC has pursued companies whose privacy policies did not match what they actually did with data.</td>
      </tr>
    </table>
    <p class="note">Note: Laws change. Verify current text on the official sites listed in the Sources section before relying on any summary here.</p>
  </div>
</section>

<section id="quiz">
  <h2>Part 3: Check Your Understanding</h2>
  <p>Answer each question. Feedback appears immediately and explains <em>why</em>.</p>
  <div id="quiz"></div>
  <p id="score" class="score"></p>
</section>

<section id="lab">
  <h2>Part 4: Privacy Lab (Interactive Code)</h2>
  <p>Below is a fictional fitness app called <strong>FitTrack</strong>. Choose the permissions you would grant, then check the risk. This is an interactive model, not a real app.</p>
  <div class="card">
    <div id="permissions"></div>
    <button id="checkRisk" class="primary">Check my risk</button>
    <div id="riskResult" class="result" aria-live="polite"></div>
  </div>
  <div class="card">
    <h3>Reflect</h3>
    <p>Your score reflects <em>choices</em>, not the app's intentions. What would have to be true for "Always" location access to be justified? Who should decide?</p>
  </div>
</section>

<section id="sources">
  <h2>Sources and Citations</h2>
  <ol>
    <li>European Union. <em>Regulation (EU) 2016/679 (General Data Protection Regulation)</em>. EUR-Lex. https://eur-lex.europa.eu/eli/reg/2016/679/oj</li>
    <li>California Legislative Information. <em>California Civil Code §1798.100 et seq. (CCPA as amended)</em>. https://leginfo.legislature.ca.gov</li>
    <li>Federal Trade Commission. <em>Section 5 of the FTC Act: Unfair or Deceptive Acts or Practices</em>. https://www.ftc.gov</li>
    <li>Association for Computing Machinery. <em>ACM Code of Ethics and Professional Conduct</em>. https://www.acm.org/code-of-ethics</li>
  </ol>
  <p class="note">Replace or add to these with sources you've checked yourself, and cite the specific section you used.</p>
</section>

<section id="peer">
  <h2>Peer Testing</h2>
  <p>Ask a classmate to complete these steps and record what happened:</p>
  <ol>
    <li>Open the site on a phone and on a laptop. Did everything display correctly?</li>
    <li>Finish the quiz without reading. Was the feedback clear enough to learn from?</li>
    <li>Use the Privacy Lab. Was it obvious what to do?</li>
    <li>Tell me one thing that was confusing or could be improved.</li>
  </ol>
  <div class="card">
    <h3>My improvement based on feedback</h3>
    <p>Write the feedback you received, the change you made, and why. Edit this paragraph after testing.</p>
  </div>
</section>

</main>

<footer>
  <p>Created for the Safety, Law, and Ethics in Computing assessment.</p>
</footer>

<script src="app.js"></script>
</body>
</html>
""";

    // ------------------------------------------------------------------
    // style.css
    // ------------------------------------------------------------------
    private static final String STYLE_CSS = """
:root {
  --bg: #f6f7fb;
  --text: #1d2330;
  --accent: #2456d6;
  --card: #ffffff;
  --right: #1e7a46;
  --wrong: #b3261e;
  --muted: #5a6275;
}
* { box-sizing: border-box; }
body {
  margin: 0;
  font-family: system-ui, -apple-system, "Segoe UI", Roboto, sans-serif;
  background: var(--bg);
  color: var(--text);
  line-height: 1.6;
}
header {
  background: var(--accent);
  color: #fff;
  padding: 2rem 1.5rem 1rem;
  text-align: center;
}
header h1 { margin: 0 0 .25rem; font-size: 1.8rem; }
.subtitle { margin: 0 0 1rem; opacity: .9; }
nav { display: flex; flex-wrap: wrap; justify-content: center; gap: .5rem; }
nav a {
  color: #fff; text-decoration: none; padding: .35rem .75rem;
  border: 1px solid rgba(255,255,255,.6); border-radius: 999px; font-size: .9rem;
}
nav a:hover, nav a:focus { background: rgba(255,255,255,.2); }
main { max-width: 860px; margin: 0 auto; padding: 1rem; }
section { margin: 2rem 0; }
h2 { border-bottom: 2px solid var(--accent); padding-bottom: .25rem; }
.card {
  background: var(--card); border-radius: 12px; padding: 1.25rem;
  margin: 1rem 0; box-shadow: 0 1px 4px rgba(0,0,0,.08);
}
.scenario { border-left: 5px solid var(--accent); }
.note { color: var(--muted); font-size: .9rem; }
table { width: 100%; border-collapse: collapse; font-size: .95rem; }
th, td { text-align: left; padding: .6rem; border-bottom: 1px solid #e3e6ee; vertical-align: top; }
th { background: #eef2fb; }
button {
  font: inherit; cursor: pointer; border-radius: 8px; border: 1px solid #c8cfe0;
  background: #fff; padding: .5rem .9rem; margin: .25rem .25rem .25rem 0;
}
button:hover:not(:disabled) { border-color: var(--accent); }
button:disabled { cursor: default; }
button.primary { background: var(--accent); color: #fff; border-color: var(--accent); }
button.right { background: #dff3e7; border-color: var(--right); }
button.wrong { background: #fbe3e1; border-color: var(--wrong); }
.feedback { min-height: 1.5em; margin: .5rem 0 0; }
.feedback.right { color: var(--right); }
.feedback.wrong { color: var(--wrong); }
.score { font-weight: 600; font-size: 1.1rem; }
.result { margin-top: 1rem; padding: 1rem; border-radius: 8px; background: #eef2fb; }
.result ul { margin: .5rem 0 0; padding-left: 1.25rem; }
label.perm { display: block; padding: .35rem 0; }
footer { text-align: center; color: var(--muted); padding: 2rem 1rem; font-size: .9rem; }
@media (max-width: 600px) {
  header h1 { font-size: 1.4rem; }
  th, td { font-size: .85rem; }
}
""";

    // ------------------------------------------------------------------
    // app.js
    // ------------------------------------------------------------------
    private static final String APP_JS = """
// ---------- Quiz data ----------
const quiz = [
  {
    q: "Applications that record your location using GPS are common. Which of the following is a legitimate concern when using these apps?",
    options: [
      "They drain battery a little",
      "Location history can reveal your home, workplace, and daily routine",
      "They make the screen brighter",
      "They use a lot of storage space"
    ],
    answer: 1,
    why: "Battery and storage are minor inconveniences. A location history can expose where you live, work, and travel, which is why it is treated as sensitive data under laws such as the GDPR and the CPRA."
  },
  {
    q: "What data most likely could be captured when using a navigation system?",
    options: [
      "Your fingerprint",
      "Your bank PIN",
      "Destinations, routes, and timestamps of your trips",
      "Your browser's saved passwords"
    ],
    answer: 2,
    why: "Navigation apps naturally record where you asked to go, the path you took, and when. A bank PIN and saved passwords are not something a map app would normally access."
  },
  {
    q: "What is the purpose of non-disclosure agreements (NDAs)?",
    options: [
      "To make a company's website load faster",
      "To give employees a raise",
      "To legally require that confidential information be kept private",
      "To register a trademark"
    ],
    answer: 2,
    why: "An NDA is a contract. It creates a legal obligation to keep specified information confidential. It is an example of how law supports an ethical duty of confidentiality."
  },
  {
    q: "Which of the following options can assist you in protecting your privacy?",
    options: [
      "Reusing one password for every account",
      "Posting your home address publicly",
      "Sharing your login details with friends",
      "Using a password manager with two-factor authentication"
    ],
    answer: 3,
    why: "A password manager lets you use unique strong passwords, and two-factor authentication blocks most attacks that succeed with a stolen password alone."
  },
  {
    q: "Which of the following is NOT a concern when trying to protect your Internet privacy?",
    options: [
      "Third-party trackers embedded in websites",
      "Data brokers selling profiles built from your activity",
      "Interception of data on unsecured public Wi-Fi",
      "Using strong, unique passwords for each account"
    ],
    answer: 3,
    why: "Strong, unique passwords are a protection, not a threat. The other three are real privacy concerns that people should understand."
  }
];

function renderQuiz() {
  const box = document.getElementById("quiz");
  let answered = 0;
  let correct = 0;

  const scoreEl = document.getElementById("score");
  function updateScore() {
    scoreEl.textContent = "Score: " + correct + " of " + quiz.length + " correct" +
      (answered === quiz.length ? " (complete)" : "");
  }

  quiz.forEach((item, i) => {
    const card = document.createElement("div");
    card.className = "card";

    const q = document.createElement("p");
    q.className = "question";
    q.textContent = (i + 1) + ". " + item.q;
    card.appendChild(q);

    const feedback = document.createElement("p");
    feedback.className = "feedback";

    const buttons = item.options.map((opt, j) => {
      const btn = document.createElement("button");
      btn.textContent = opt;
      btn.className = "option";
      btn.addEventListener("click", () => {
        if (card.dataset.done) return;
        card.dataset.done = "true";
        answered++;
        const isCorrect = j === item.answer;
        if (isCorrect) correct++;

        buttons.forEach(b => b.disabled = true);
        btn.classList.add(isCorrect ? "right" : "wrong");
        buttons[item.answer].classList.add("right");

        feedback.className = "feedback " + (isCorrect ? "right" : "wrong");
        feedback.textContent = (isCorrect ? "Correct. " : "Not quite. ") + item.why;
        updateScore();
      });
      card.appendChild(btn);
      return btn;
    });

    card.appendChild(feedback);
    box.appendChild(card);
  });

  updateScore();
}

// ---------- Ethics vs. law sorter ----------
const sorterItems = [
  {
    text: "A company collects location data without telling users, even though no statute specifically forbids it in their state.",
    answer: "ethical",
    why: "This is primarily an ethical problem: the lack of transparency is wrong even where the law does not yet prohibit it. Some jurisdictions may now have rules that cover it, so check the law where you are."
  },
  {
    text: "A company in the EU processes location data without a lawful basis such as consent.",
    answer: "legal",
    why: "The GDPR requires a lawful basis for processing personal data, so this would violate a specific regulation. Ethics also applies, but the central issue here is a legal requirement."
  },
  {
    text: "A developer decides to store passwords in plain text because it is faster to build, even though no law in their country directly addresses it.",
    answer: "both",
    why: "Plain-text passwords are an ethical failure because they put users at risk. Depending on the jurisdiction and the data involved, they may also breach data-protection or security obligations."
  },
  {
    text: "A software vendor sells personal profiles to data brokers, and its privacy policy says it does not sell data.",
    answer: "both",
    why: "The deception is ethically problematic, and it can also be unfair or deceptive under laws such as Section 5 of the FTC Act in the United States."
  }
];

function renderSorter() {
  const box = document.getElementById("sorter");
  sorterItems.forEach((item, i) => {
    const row = document.createElement("div");
    row.className = "card";
    const p = document.createElement("p");
    p.textContent = (i + 1) + ". " + item.text;
    row.appendChild(p);

    const feedback = document.createElement("p");
    feedback.className = "feedback";

    ["ethical", "legal", "both"].forEach(choice => {
      const btn = document.createElement("button");
      btn.textContent = choice.charAt(0).toUpperCase() + choice.slice(1);
      btn.addEventListener("click", () => {
        const ok = choice === item.answer;
        feedback.className = "feedback " + (ok ? "right" : "wrong");
        feedback.textContent = (ok ? "Matches. " : "Consider again. The best answer is: " + item.answer + ". ") + item.why;
      });
      row.appendChild(btn);
    });

    row.appendChild(feedback);
    box.appendChild(row);
  });
}

// ---------- Privacy Lab (interactive code) ----------
const permissions = [
  { id: "loc-always", label: "Location: Always (including in background)", risk: 3,
    note: "Background access can build a detailed movement history. Choose 'While using the app' unless a feature truly needs more." },
  { id: "loc-using", label: "Location: While using the app", risk: 1,
    note: "Limited to when you are actively using the app. Lower exposure." },
  { id: "contacts", label: "Contacts", risk: 2,
    note: "Shares other people's information, who never agreed to the app. Consider whether the feature needs it." },
  { id: "microphone", label: "Microphone", risk: 2,
    note: "Can capture audio in unexpected moments. Grant only if you use voice features." },
  { id: "camera", label: "Camera", risk: 1,
    note: "Usually low risk when limited to the app's features, but still review why it is needed." },
  { id: "health", label: "Health and activity data", risk: 2,
    note: "Health data is especially sensitive and is protected more strictly under many laws." },
  { id: "tracking", label: "Allow cross-app tracking for ads", risk: 3,
    note: "Lets data follow you across other companies' apps and websites. Often the most privacy-costly choice." }
];

function renderPermissions() {
  const box = document.getElementById("permissions");
  permissions.forEach(p => {
    const label = document.createElement("label");
    label.className = "perm";
    const cb = document.createElement("input");
    cb.type = "checkbox";
    cb.id = p.id;
    label.appendChild(cb);
    label.appendChild(document.createTextNode(" " + p.label));
    box.appendChild(label);
  });
}

function checkRisk() {
  const result = document.getElementById("riskResult");
  const chosen = permissions.filter(p => document.getElementById(p.id).checked);

  if (chosen.length === 0) {
    result.textContent = "You granted no permissions. The app may not work well, but your exposure is minimal.";
    return;
  }

  const total = chosen.reduce((sum, p) => sum + p.risk, 0);
  const max = permissions.reduce((sum, p) => sum + p.risk, 0);
  const level = total / max < 0.35 ? "Low" : total / max < 0.65 ? "Moderate" : "High";

  result.innerHTML = "";
  const heading = document.createElement("strong");
  heading.textContent = "Overall exposure: " + level + " (" + total + " of " + max + " risk points)";
  result.appendChild(heading);

  const list = document.createElement("ul");
  chosen.forEach(p => {
    const li = document.createElement("li");
    li.textContent = p.label + ": " + p.note;
    list.appendChild(li);
  });
  result.appendChild(list);
}

// ---------- Startup ----------
document.addEventListener("DOMContentLoaded", () => {
  renderSorter();
  renderQuiz();
  renderPermissions();
  document.getElementById("checkRisk").addEventListener("click", checkRisk);
});
""";
}